package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.model.MyReceiverModel;
import com.quduo.welfareshop.ui.mine.view.IMyReceiverView;

/**
 * Author:scene
 * Time:2018/3/1 14:08
 * Description:收货信息
 */

public class MyReceiverPresenter extends BasePresenter<IMyReceiverView> {
    private MyReceiverModel model;

    public MyReceiverPresenter(IMyReceiverView view) {
        super(view);
        this.model = new MyReceiverModel();
    }
}
