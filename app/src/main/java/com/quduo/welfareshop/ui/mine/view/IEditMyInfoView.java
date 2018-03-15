package com.quduo.welfareshop.ui.mine.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;
import com.quduo.welfareshop.ui.mine.entity.UploadAvatarResultInfo;

import java.util.List;

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

    void uploadPhotoSuccess(List<MyUserDetailInfo.PhotosBean> data);

    void deletePhotoSuccess(int position);
}
