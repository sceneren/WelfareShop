package com.quduo.welfareshop.ui.shop.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.shop.entity.ConfirmOrderResultInfo;
import com.quduo.welfareshop.ui.shop.entity.PayInfo;

/**
 * Author:scene
 * Time:2018/3/13 15:35
 * Description:确认订单
 */

public interface IConfirmOrderView extends BaseView {
    void bindData(ConfirmOrderResultInfo data);

    void showMessage(String message);

    void showLoadingDialog();

    void hideLaodingDialog();

    void createOrderSuccess(PayInfo data);
}
