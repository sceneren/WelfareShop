package com.quduo.welfareshop.ui.red.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.red.model.UnparkRedModel;
import com.quduo.welfareshop.ui.red.view.IUnparkRedView;

/**
 * Author:scene
 * Time:2018/3/8 9:59
 * Description:未拆开的红包
 */

public class UnparkRedPresenter extends BasePresenter<IUnparkRedView> {
    private UnparkRedModel model;

    public UnparkRedPresenter(IUnparkRedView view) {
        this.mView = view;
        this.model = new UnparkRedModel();
    }
}
