package com.quduo.welfareshop.ui.shop.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.shop.model.ConfirmOrderModel;
import com.quduo.welfareshop.ui.shop.view.IConfirmOrderView;

/**
 * Author:scene
 * Time:2018/3/13 15:35
 * Description:确认订单
 */

public class ConfirmOrderPresenter extends BasePresenter<IConfirmOrderView> {
    private ConfirmOrderModel model;

    public ConfirmOrderPresenter(IConfirmOrderView view) {
        super(view);
        this.model = new ConfirmOrderModel();
    }
}
