package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.model.OrderDetailModel;
import com.quduo.welfareshop.ui.mine.view.IOrderDetailView;

/**
 * Author:scene
 * Time:2018/3/14 10:13
 * Description:订单详情
 * 1未支付  2已支付  3已发货  4已评价  5已取消
 */

public class OrderDetailPresenter extends BasePresenter<IOrderDetailView> {
    private OrderDetailModel model;

    public OrderDetailPresenter(IOrderDetailView view) {
        super(view);
        this.model = new OrderDetailModel();
    }
}
