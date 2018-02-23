package com.quduo.welfareshop.ui.welfare.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseViewPagerAdapter;
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.mvp.BaseMainMvpFragment;
import com.quduo.welfareshop.ui.mine.fragment.MineFragment;
import com.quduo.welfareshop.ui.welfare.presenter.WelfarePresenter;
import com.quduo.welfareshop.ui.welfare.view.IWelfareView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jzvd.JZVideoPlayer;

/**
 * 福利主界面
 * Created by scene on 2018/1/25.
 */

public class WelfareFragment extends BaseMainMvpFragment<IWelfareView, WelfarePresenter> implements IWelfareView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public static WelfareFragment newInstance() {
        Bundle args = new Bundle();
        WelfareFragment fragment = new WelfareFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
        toolbarTitle.setText("福利");
    }

    @Override
    public void initView() {
        super.initView();
        String tabTitle[] = {"美女图库","小视频", "美女视频", "午夜影院", "小爽文"};
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(GalleryFragment.newInstance());
        fragmentList.add(SmallVideoFragment.newInstance());
        fragmentList.add(BeautyVideoFragment.newInstance());
        fragmentList.add(GalleryFragment.newInstance());
        fragmentList.add(GalleryFragment.newInstance());
        tab.addTab(tab.newTab().setText(tabTitle[0]));
        tab.addTab(tab.newTab().setText(tabTitle[1]));
        tab.addTab(tab.newTab().setText(tabTitle[2]));
        tab.addTab(tab.newTab().setText(tabTitle[3]));
        tab.addTab(tab.newTab().setText(tabTitle[4]));
        viewPager.setAdapter(new BaseViewPagerAdapter(getChildFragmentManager(), tabTitle, fragmentList));
        viewPager.setOffscreenPageLimit(5);
        tab.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                try {
                    JZVideoPlayer.releaseAllVideos();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void showLoadingPage() {

    }

    @Override
    public void showContentPage() {

    }

    @Override
    public void showErrorPage() {

    }

    @Override
    public WelfarePresenter initPresenter() {
        return new WelfarePresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.toolbar_image_menu)
    public void onClickToolBarImageMenu() {
        EventBus.getDefault().post(new StartBrotherEvent(MineFragment.newInstance()));
    }

    @Override
    public boolean onBackPressedSupport() {
        try {
            if (JZVideoPlayer.backPress()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onBackPressedSupport();
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            JZVideoPlayer.releaseAllVideos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
