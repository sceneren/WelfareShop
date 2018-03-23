package com.quduo.welfareshop.ui.mine.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.mine.entity.OrderDetailResultInfo;
import com.quduo.welfareshop.ui.shop.entity.PayInfo;

/**
 * Author:scene
 * Time:2018/3/14 10:13
 * Description:订单详情
 * 1未支付  2已支付  3已发货  4已评价  5已取消
 */

public interface IOrderDetailView extends BaseView {
    int getOrderId();

    void showMessage(String message);

    void refreshFinish();

    void bindData(OrderDetailResultInfo data);

    void showLoadingDialog();

    void hideLoadingDialog();

    void alert(String message);

    void repayOrderSuccess(PayInfo payInfo);
}
