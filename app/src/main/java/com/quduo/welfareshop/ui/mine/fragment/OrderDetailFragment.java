package com.quduo.welfareshop.ui.mine.fragment;

import android.os.Bundle;

import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.mine.presenter.OrderDetailPresenter;
import com.quduo.welfareshop.ui.mine.view.IOrderDetailView;

/**
 * Author:scene
 * Time:2018/3/14 10:13
 * Description:订单详情
 * 1未支付  2已支付  3已发货  4已评价  5已取消
 */

public class OrderDetailFragment extends BaseBackMvpFragment<IOrderDetailView, OrderDetailPresenter> implements IOrderDetailView {
    private static final String ARG_ID = "order_id";

    public static OrderDetailFragment newInstance(int orderId) {
        Bundle args = new Bundle();
        OrderDetailFragment fragment = new OrderDetailFragment();

        fragment.setArguments(args);
        return fragment;
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

    }

    @Override
    public void initView() {

    }

    @Override
    public OrderDetailPresenter initPresenter() {
        return new OrderDetailPresenter(this);
    }
}
