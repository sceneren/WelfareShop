package com.quduo.welfareshop.ui.mine.fragment;

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
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.mine.presenter.MyFollowPresenter;
import com.quduo.welfareshop.ui.mine.view.IMyFollowView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/3/1 11:17
 * Description:我的收藏
 */

public class MyFollowFragment extends BaseBackMvpFragment<IMyFollowView, MyFollowPresenter> implements IMyFollowView {
    private static final String ARG_TYPE = "arg_type";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    private int type;

    public static MyFollowFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        MyFollowFragment fragment = new MyFollowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(ARG_TYPE, 0);
        } else {
            type = 0;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_follow, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
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
    public void initToolbar() {
        toolbarTitle.setText("我的收藏");
        initToolbarNav(toolbar, true);
    }

    @Override
    public void initView() {
        String tabTitle[] = {"视频",  "图片"};
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(MyFollowVideoFragment.newInstance());
        //fragmentList.add(MyFollowNovelFragment.newInstance());
        fragmentList.add(MyFollowImageFragment.newInstance());
        tab.addTab(tab.newTab().setText(tabTitle[0]));
        tab.addTab(tab.newTab().setText(tabTitle[1]));
        //tab.addTab(tab.newTab().setText(tabTitle[2]));
        viewPager.setAdapter(new BaseViewPagerAdapter(getChildFragmentManager(), tabTitle, fragmentList));
        viewPager.setOffscreenPageLimit(3);
        tab.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(type);
    }

    @Override
    public MyFollowPresenter initPresenter() {
        return new MyFollowPresenter(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
