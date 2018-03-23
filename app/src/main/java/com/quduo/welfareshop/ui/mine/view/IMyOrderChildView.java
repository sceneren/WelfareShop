package com.quduo.welfareshop.ui.mine.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.mine.entity.OrderListResultInfo;

/**
 * Author:scene
 * Time:2018/2/28 18:02
 * Description:订单子页面
 */

public interface IMyOrderChildView extends BaseView {
    int getOrderType();

    void bindData(OrderListResultInfo data);

    void showMessage(String message);

    void refreshFinish();

    void loadmoreFinish();

    void setHasmore(boolean hasmore);

    void showLoadingDialog();

    void hideLoadingDialog();

    void cancelOrderSuccess(int position);

}
