package com.quduo.welfareshop.ui.friend.view;

import com.quduo.welfareshop.mvp.BaseView;

public interface IUserAllVideoView extends BaseView {
    void showMessage(String message);

    void alert(String message);

    void showLoadingDialog();

    void hideLoadingDialog();
}
