package com.quduo.welfareshop.activity;

import com.quduo.welfareshop.mvp.BasePresenter;

/**
 * Author:scene
 * Time:2018/3/5 17:50
 * Description:充值
 */

public class RechargePresenter extends BasePresenter<IRechargeView> {
    private RechargeModel model;

    public RechargePresenter(IRechargeView view) {
        super(view);
        this.model = new RechargeModel();
    }
}
