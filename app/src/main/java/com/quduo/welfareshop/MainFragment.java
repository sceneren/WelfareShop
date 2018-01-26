package com.quduo.welfareshop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.event.TabSelectedEvent;
import com.quduo.welfareshop.ui.encyc.fragment.EncycFragment;
import com.quduo.welfareshop.ui.limit.fragment.LimitFragment;
import com.quduo.welfareshop.ui.mine.fragment.MineFragment;
import com.quduo.welfareshop.ui.shop.fragment.ShopFragment;
import com.quduo.welfareshop.ui.welfare.fragment.WelfareFragment;
import com.quduo.welfareshop.widgets.bottombar.BottomBar;
import com.quduo.welfareshop.widgets.bottombar.BottomBarTab;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 主Fragment
 * Created by scene on 2018/1/25.
 */

public class MainFragment extends SupportFragment {
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;
    public static final int FIVE = 4;
    @BindView(R.id.fl_tab_container)
    FrameLayout flTabContainer;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    Unbinder unbinder;

    private SupportFragment[] mFragments = new SupportFragment[5];
    private List<String> tabNames = new ArrayList<>();

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment firstFragment = findChildFragment(WelfareFragment.class);
        tabNames.add(getString(R.string.tab_welfare));
        tabNames.add(getString(R.string.tab_limit));
        tabNames.add(getString(R.string.tab_shop));
        tabNames.add(getString(R.string.tab_encyc));
        tabNames.add(getString(R.string.tab_mine));
        if (firstFragment == null) {
            mFragments[FIRST] = WelfareFragment.newInstance();
            mFragments[SECOND] = LimitFragment.newInstance();
            mFragments[THIRD] = ShopFragment.newInstance();
            mFragments[FOUR] = EncycFragment.newInstance();
            mFragments[FIVE] = MineFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOUR],
                    mFragments[FIVE]);
        } else {
            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.findFragmentByTag自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(LimitFragment.class);
            mFragments[THIRD] = findChildFragment(ShopFragment.class);
            mFragments[FOUR] = findChildFragment(EncycFragment.class);
            mFragments[FIVE] = findChildFragment(MineFragment.class);
        }
        initView();
    }

    private void initView() {
        bottomBar
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_bottombar_index_d, R.drawable.ic_bottombar_index_s, getString(R.string.tab_welfare)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_bottombar_discover_d, R.drawable.ic_bottombar_discover_s, getString(R.string.tab_limit)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_bottombar_wincode_d, R.drawable.ic_bottombar_wincode_s, getString(R.string.tab_shop)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_bottombar_mine_d, R.drawable.ic_bottombar_mine_s, getString(R.string.tab_encyc)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_bottombar_mine_d, R.drawable.ic_bottombar_mine_s, getString(R.string.tab_mine)));


        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }

    @Subscribe
    public void startBrother(StartBrotherEvent event) {
        start(event.targetFragment);
    }

    @Subscribe
    public void toIndexPage(TabSelectedEvent event) {
        bottomBar.setCurrentItem(event.position);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }
}
