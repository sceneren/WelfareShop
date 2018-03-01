package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.model.MyOrderChildModel;
import com.quduo.welfareshop.ui.mine.view.IMyOrderChildView;

/**
 * Author:scene
 * Time:2018/2/28 18:07
 * Description:订单子页面
 */

public class MyOrderChildPresenter extends BasePresenter<IMyOrderChildView> {
    private MyOrderChildModel model;

    public MyOrderChildPresenter(IMyOrderChildView view) {
        this.mView = view;
        this.model = new MyOrderChildModel();
    }
}
