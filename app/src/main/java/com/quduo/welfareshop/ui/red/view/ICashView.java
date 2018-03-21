package com.quduo.welfareshop.ui.red.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.red.entity.CashResultInfo;

/**
 * Author:scene
 * Time:2018/3/8 11:28
 * Description:提现
 */

public interface ICashView extends BaseView {
    void showMessage(String message);

    void showLoadingDialog();

    void hideLoadingDialog();

    void cashSuccess();

    double getCost();

    String getBankBankName();

    String getBankBankCard();

    String getBankUsername();

    String getAlipayUsername();

    String getAlipayAlipayCard();
}
