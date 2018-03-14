package com.quduo.welfareshop.ui.indiana.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.indiana.model.IndianaModel;
import com.quduo.welfareshop.ui.indiana.view.IIndianaView;

/**
 * 夺宝首页
 * Created by scene on 2018/1/29.
 */

public class IndianaPresenter extends BasePresenter<IIndianaView> {
    private IndianaModel model;

    public IndianaPresenter(IIndianaView view) {
        super(view);
        this.model = new IndianaModel();
    }
}
