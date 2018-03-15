package com.quduo.welfareshop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.entity.UploadAvatarResultInfo;
import com.quduo.welfareshop.ui.mine.model.EditMyInfoModel;
import com.quduo.welfareshop.ui.mine.view.IEditMyInfoView;

import java.io.File;

/**
 * Author:scene
 * Time:2018/2/2 16:38
 * Description:编辑资料
 */

public class EditMyInfoPresenter extends BasePresenter<IEditMyInfoView> {
    private EditMyInfoModel model;

    public EditMyInfoPresenter(IEditMyInfoView view) {
        super(view);
        model = new EditMyInfoModel();
    }

    public void uploadAvatar(String avatarFilePath) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("files", new File(avatarFilePath));
            model.uploadAvatar(params, new HttpResultListener<UploadAvatarResultInfo>() {
                @Override
                public void onSuccess(UploadAvatarResultInfo data) {
                    try {
                        mView.uploadAvaterSuccess(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        mView.hideLoadingDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
