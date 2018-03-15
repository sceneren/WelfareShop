package com.quduo.welfareshop.ui.friend.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.friend.entity.OtherSimpleUserInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/1 17:07
 * Description:附近的人
 */

public interface IRankView extends BaseView {
    void bindData(List<OtherSimpleUserInfo> data);

    void refreshFinish();

    void showMessage(String message);

    void showLoadingDialog();

    void hideLoadingDialog();

    double getLatitude();

    double getLongitude();

    void followSuccess(int position, int followId);
}
