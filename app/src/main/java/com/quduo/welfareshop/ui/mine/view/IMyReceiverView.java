package com.quduo.welfareshop.ui.mine.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.mine.entity.ReceiverInfo;

/**
 * Author:scene
 * Time:2018/3/1 14:08
 * Description:收货信息
 */

public interface IMyReceiverView extends BaseView {
    void showLaodingDialog();

    void hideLoadingDialog();

    void showMessage(String message);

    void bindData(ReceiverInfo data);

    String getName();

    String getPhone();

    String getAddress();
}
