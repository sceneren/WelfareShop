package com.quduo.welfareshop.ui.friend.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhh.apst.library.CustomPagerSlidingTabStrip;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.mvp.BaseMainMvpFragment;
import com.quduo.welfareshop.ui.friend.adapter.FriendPagerAdapter;
import com.quduo.welfareshop.ui.friend.presenter.FriendPresenter;
import com.quduo.welfareshop.ui.friend.view.IFriendView;
import com.quduo.welfareshop.ui.mine.fragment.MineFragment;
import com.quduo.welfareshop.widgets.APSTSViewPager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/2/1 15:18
 * Description:
 */

public class FriendFragment extends BaseMainMvpFragment<IFriendView, FriendPresenter> implements IFriendView {

    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_image_menu)
    ImageView toolbarImageMenu;
    @BindView(R.id.tabs)
    CustomPagerSlidingTabStrip tabs;
    @BindView(R.id.viewPager)
    APSTSViewPager viewPager;

    public static FriendFragment newInstance() {
        Bundle args = new Bundle();
        FriendFragment fragment = new FriendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
        toolbarTitle.setText(getString(R.string.tab_friend));
    }

    @Override
    public void initView() {
        super.initView();
        List<String> tabTitle = new ArrayList<>();
        tabTitle.add("附近的人");
        tabTitle.add("人气榜");
        tabTitle.add("我的关注");
        tabTitle.add("消息");
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(NearFragment.newInstance());
        fragmentList.add(RankFragment.newInstance());
        fragmentList.add(FollowFragment.newInstance());
        fragmentList.add(MessageFragment.newInstance());
        viewPager.setNoFocus(true);
        viewPager.setOffscreenPageLimit(tabTitle.size());
        viewPager.setAdapter(new FriendPagerAdapter(getContext(), getChildFragmentManager(), fragmentList, tabTitle));
        tabs.setViewPager(viewPager);
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
    public FriendPresenter initPresenter() {
        return new FriendPresenter(this);
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
}
