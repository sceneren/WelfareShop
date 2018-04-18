package com.quduo.welfareshop.ui.friend.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.welfare.entity.UnlockResultInfo;

public interface IFriendVideoDetailView extends BaseView {
    void showLoadingDialog();

    void hideLoadingDialog();

    void showMessage(String message);

    void unlockSuccess(int currentScore);
}
