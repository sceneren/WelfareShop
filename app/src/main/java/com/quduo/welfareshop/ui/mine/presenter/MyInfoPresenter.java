package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.model.MyInfoModel;
import com.quduo.welfareshop.ui.mine.view.IMyInfoView;

/**
 * Author:scene
 * Time:2018/2/2 15:47
 * Description:我的资料
 */
public class MyInfoPresenter extends BasePresenter<IMyInfoView> {
    private MyInfoModel model;

    public MyInfoPresenter(IMyInfoView view) {
        super(view);
        model = new MyInfoModel();
    }
}
