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

import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseViewPagerAdapter;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.mine.presenter.MyOrderPresenter;
import com.quduo.welfareshop.ui.mine.view.IMyOrderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/2/28 17:57
 * Description:我的订单
 */

public class MyOrderFragment extends BaseBackMvpFragment<IMyOrderView, MyOrderPresenter> implements IMyOrderView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    public static MyOrderFragment newInstance() {
        Bundle args = new Bundle();
        MyOrderFragment fragment = new MyOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_order, container, false);
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
        toolbarTitle.setText("我的订单");
        initToolbarNav(toolbar, true);
    }

    @Override
    public void initView() {
        String tabTitle[] = {"待付款", "待发货", "已发货"};
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(MyOrderChildFragment.newInstance(1));
        fragmentList.add(MyOrderChildFragment.newInstance(2));
        fragmentList.add(MyOrderChildFragment.newInstance(3));
        tab.addTab(tab.newTab().setText(tabTitle[0]));
        tab.addTab(tab.newTab().setText(tabTitle[1]));
        tab.addTab(tab.newTab().setText(tabTitle[2]));
        viewPager.setAdapter(new BaseViewPagerAdapter(getChildFragmentManager(), tabTitle, fragmentList));
        viewPager.setOffscreenPageLimit(3);
        tab.setupWithViewPager(viewPager);
    }

    @Override
    public MyOrderPresenter initPresenter() {
        return new MyOrderPresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.ORDER_LIST_TAG);
        OkGo.getInstance().cancelTag(ApiUtil.CANCEL_ORDER_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }
}
