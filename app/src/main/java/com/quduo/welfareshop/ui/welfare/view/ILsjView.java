package com.quduo.welfareshop.ui.welfare.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.welfare.entity.LsjInfo;

public interface ILsjView extends BaseView {
    void refreshFinish1();

    void refreshFinish2();

    void showMessage(String message);

    void alert(String message);

    void bindData(LsjInfo data);

    void showLoadingDialog();

    void hideLoadingDialog();

    void unlockSuccess(int score);
}
