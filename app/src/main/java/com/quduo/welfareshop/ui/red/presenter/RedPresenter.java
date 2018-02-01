package com.quduo.welfareshop.ui.red.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.red.model.RedModel;
import com.quduo.welfareshop.ui.red.view.IRedView;

/**
 * Author:scene
 * Time:2018/2/1 15:25
 * Description:
 */

public class RedPresenter extends BasePresenter<IRedView> {
    private RedModel model;

    public RedPresenter(IRedView view) {
        this.mView = view;
        this.model = new RedModel();
    }
}
