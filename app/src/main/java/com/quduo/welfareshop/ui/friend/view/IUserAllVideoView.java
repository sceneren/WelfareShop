package com.quduo.welfareshop.ui.friend.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.friend.entity.UserVideoInfo;

import java.util.List;

public interface IUserAllVideoView extends BaseView {
    void showMessage(String message);

    void alert(String message);

    void showLoadingDialog();

    void hideLoadingDialog();

    void unlockSuccess(int position, int currentScore);

    void refreshFinish();

    void bindData(List<UserVideoInfo> data);
}
