package com.quduo.welfareshop.ui.shop.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.entity.CheckPayResultInfo;
import com.quduo.welfareshop.ui.shop.entity.ConfirmOrderResultInfo;
import com.quduo.welfareshop.ui.shop.entity.PayInfo;
import com.quduo.welfareshop.ui.shop.model.ConfirmOrderModel;
import com.quduo.welfareshop.ui.shop.view.IConfirmOrderView;

/**
 * Author:scene
 * Time:2018/3/13 15:35
 * Description:确认订单
 */

public class ConfirmOrderPresenter extends BasePresenter<IConfirmOrderView> {
    private ConfirmOrderModel model;

    public ConfirmOrderPresenter(IConfirmOrderView view) {
        super(view);
        this.model = new ConfirmOrderModel();
    }

    public void getData() {
        try {
            mView.showLoadingPage();
            model.getData(new HttpResultListener<ConfirmOrderResultInfo>() {
                @Override
                public void onSuccess(ConfirmOrderResultInfo data) {
                    try {
                        mView.bindData(data);
                        mView.showContentPage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
                        mView.showErrorPage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createOrder(int goodsId, int number, String choosedModel, String receiverName, String receiverPhone, String receiverAddress, int couponId, int payType, String remark) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("product_id", goodsId);
            params.put("number", number);
            params.put("model", choosedModel);
            params.put("name", receiverName);
            params.put("mobile", receiverPhone);
            params.put("address", receiverAddress);
            params.put("coupon_id", couponId);
            params.put("pay_type", payType);
            params.put("remark", remark);
            model.createOrder(params, new HttpResultListener<PayInfo>() {
                @Override
                public void onSuccess(PayInfo data) {
                    try {
                        mView.createOrderSuccess(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        mView.hideLaodingDialog();
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
                        mView.hideLaodingDialog();
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
