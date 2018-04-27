package com.quduo.welfareshop.ui.welfare.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.model.LsjModel;
import com.quduo.welfareshop.ui.welfare.view.ILsjView;

public class LsjPresenter extends BasePresenter<ILsjView> {

    private LsjModel model;

    public LsjPresenter(ILsjView view) {
        super(view);
        this.model = new LsjModel();
    }
}
