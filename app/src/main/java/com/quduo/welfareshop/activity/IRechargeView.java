package com.quduo.welfareshop.activity;

import com.quduo.welfareshop.bean.RechargeInfo;
import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.mine.entity.CheckPayResultInfo;
import com.quduo.welfareshop.ui.shop.entity.PayInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/5 17:50
 * Description:充值
 */

public interface IRechargeView extends BaseView {
    void bindRechargeListView(RechargeInfo data);

    void showMessage(String message);

    void alert(String message);

    void showLoadingDialog();

    void hideLoadingDialog();

    void getPayInfoSuccess(PayInfo payInfo);

    void paySuccess(CheckPayResultInfo data);

}
