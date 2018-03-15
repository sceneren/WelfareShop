package com.quduo.welfareshop.ui.mine.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.mine.entity.UploadAvatarResultInfo;

/**
 * Author:scene
 * Time:2018/2/2 16:38
 * Description:编辑资料
 */

public interface IEditMyInfoView extends BaseView {
    void showLoadingDialog();

    void hideLoadingDialog();

    void uploadAvaterSuccess(UploadAvatarResultInfo resultInfo);

    void showMessage(String message);
}
