package com.quduo.welfareshop;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.event.TabSelectedEvent;
import com.quduo.welfareshop.ui.friend.fragment.FriendFragment;
import com.quduo.welfareshop.ui.mine.fragment.MineFragment;
import com.quduo.welfareshop.ui.red.fragment.RedFragment;
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
import butterknife.OnClick;
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
    @BindView(R.id.image)
    ImageView image;

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
        tabNames.add(getString(R.string.tab_shop));
        tabNames.add(getString(R.string.tab_red));
        tabNames.add(getString(R.string.tab_friend));
        tabNames.add(getString(R.string.tab_mine));
        if (firstFragment == null) {
            mFragments[FIRST] = WelfareFragment.newInstance();
            mFragments[SECOND] = ShopFragment.newInstance();
            mFragments[THIRD] = RedFragment.newInstance();
            mFragments[FOUR] = FriendFragment.newInstance();
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
            mFragments[SECOND] = findChildFragment(ShopFragment.class);
            mFragments[THIRD] = findChildFragment(RedFragment.class);
            mFragments[FOUR] = findChildFragment(FriendFragment.class);
            mFragments[FIVE] = findChildFragment(MineFragment.class);
        }
        initView();
    }

    private TranslateAnimation animation;
    private boolean isWork = true;

    private void showMoveImage() {
        _mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (animation == null) {
                    animation = new TranslateAnimation(
                            Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, -1.0f,
                            Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
                    );
                    animation.setDuration(10000);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            image.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            image.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
                if (bottomBar.getCurrentItemPosition() == 0 || bottomBar.getCurrentItemPosition() == 3) {
                    image.clearAnimation();
                    image.startAnimation(animation);
                }
            }
        });
    }

    private Thread thread;

    private void startMoveImageThread() {
        if (thread == null) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isWork) {
                        SystemClock.sleep(36000);
                        showMoveImage();
                    }
                }
            });
        }
        thread.start();
    }

    private void initView() {
        GlideApp.with(this)
                .load(R.drawable.ic_move_car)
                .into(image);
        startMoveImageThread();

        bottomBar
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_tab_welfare_d, R.drawable.ic_tab_welfare_s, tabNames.get(FIRST)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_tab_shop_d, R.drawable.ic_tab_shop_s, tabNames.get(SECOND)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_tab_red_d, R.drawable.ic_tab_red_s, tabNames.get(THIRD)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_tab_friend_d, R.drawable.ic_tab_friend_s, tabNames.get(FOUR)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_tab_mine_d, R.drawable.ic_tab_mine_s, tabNames.get(FIVE)));

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

    @OnClick(R.id.image)
    public void onClickImage() {
        try {
            image.clearAnimation();
            bottomBar.setCurrentItem(1);
            image.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
