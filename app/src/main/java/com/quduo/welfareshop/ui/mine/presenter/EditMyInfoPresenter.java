package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.model.EditMyInfoModel;
import com.quduo.welfareshop.ui.mine.view.IEditMyInfoView;

/**
 * Author:scene
 * Time:2018/2/2 16:38
 * Description:编辑资料
 */

public class EditMyInfoPresenter extends BasePresenter<IEditMyInfoView> {
    private EditMyInfoModel model;

    public EditMyInfoPresenter(IEditMyInfoView view) {
        this.mView = view;
        model = new EditMyInfoModel();
    }
}
