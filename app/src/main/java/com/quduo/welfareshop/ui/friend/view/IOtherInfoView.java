package com.quduo.welfareshop.ui.friend.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.friend.entity.OtherDetailUserInfo;

/**
 * Author:scene
 * Time:2018/2/5 14:57
 * Description:别人的主页
 */

public interface IOtherInfoView extends BaseView {
    void bindData(OtherDetailUserInfo data);

    String getTargetUserId();

    double getLongitude();

    double getLatitude();

    int getFromNearby();

    void refreshFinish();

    void showMessage(String message);

    void showLoadingDialog();

    void hideLoadingDialog();

    void followSuccess(int followId);

    void cancelFollowSuccess();

    int getFollowId();

}
