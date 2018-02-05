package com.quduo.welfareshop.ui.friend.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.model.OtherInfoModel;
import com.quduo.welfareshop.ui.friend.view.IOtherInfoView;

/**
 * Author:scene
 * Time:2018/2/5 14:57
 * Description:别人的主页
 */

public class OtherInfoPresenter extends BasePresenter<IOtherInfoView> {
    private OtherInfoModel model;

    public OtherInfoPresenter(IOtherInfoView view) {
        this.mView = view;
        this.model = new OtherInfoModel();
    }
}
