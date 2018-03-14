package com.quduo.welfareshop.ui.red.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.red.model.CashModel;
import com.quduo.welfareshop.ui.red.view.ICashView;

/**
 * Author:scene
 * Time:2018/3/8 11:28
 * Description:提现
 */

public class CashPresenter extends BasePresenter<ICashView> {
    private CashModel model;

    public CashPresenter(ICashView view) {
        super(view);
        this.model = new CashModel();
    }
}
