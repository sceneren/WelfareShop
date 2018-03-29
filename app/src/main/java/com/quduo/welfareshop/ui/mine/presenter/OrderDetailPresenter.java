package com.quduo.welfareshop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.entity.CheckPayResultInfo;
import com.quduo.welfareshop.ui.mine.entity.OrderDetailResultInfo;
import com.quduo.welfareshop.ui.mine.model.OrderDetailModel;
import com.quduo.welfareshop.ui.mine.view.IOrderDetailView;
import com.quduo.welfareshop.ui.shop.entity.PayInfo;

/**
 * Author:scene
 * Time:2018/3/14 10:13
 * Description:订单详情
 * 1未支付  2已支付  3已发货  4已评价  5已取消
 */

public class OrderDetailPresenter extends BasePresenter<IOrderDetailView> {
    private OrderDetailModel model;

    public OrderDetailPresenter(IOrderDetailView view) {
        super(view);
        this.model = new OrderDetailModel();
    }

    public void getData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("order_id", mView.getOrderId());
            model.getData(params, new HttpResultListener<OrderDetailResultInfo>() {
                @Override
                public void onSuccess(OrderDetailResultInfo data) {
                    try {
                        mView.bindData(data);
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            mView.refreshFinish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    if (isFirst) {
                        mView.showContentPage();
                    } else {
                        mView.refreshFinish();
                    }
                    mView.showMessage(message);
                }

                @Override
                public void onFinish() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rePayOrder(int orderId, int payType) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("order_id", orderId);
            params.put("pay_type", payType);
            model.rePay(params, new HttpResultListener<PayInfo>() {
                @Override
                public void onSuccess(PayInfo data) {
                    try {
                        mView.repayOrderSuccess(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.alert(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        mView.hideLoadingDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkPayResult(int orderId) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("order_id", orderId);
            model.checkPaySuccess(params, new HttpResultListener<CheckPayResultInfo>() {
                @Override
                public void onSuccess(CheckPayResultInfo data) {
                    try {
                        mView.paySuccess(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.alert(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        mView.hideLoadingDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
