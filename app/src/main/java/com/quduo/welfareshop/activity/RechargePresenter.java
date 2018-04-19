package com.quduo.welfareshop.activity;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.bean.RechargeInfo;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.entity.CheckPayResultInfo;
import com.quduo.welfareshop.ui.shop.entity.PayInfo;

/**
 * Author:scene
 * Time:2018/3/5 17:50
 * Description:充值
 */

public class RechargePresenter extends BasePresenter<IRechargeView> {
    private RechargeModel model;

    public RechargePresenter(IRechargeView view) {
        super(view);
        this.model = new RechargeModel();
    }

    public void getData() {
        try {
            mView.showLoadingPage();
            model.getData(new HttpResultListener<RechargeInfo>() {
                @Override
                public void onSuccess(RechargeInfo data) {
                    try {
                        mView.bindRechargeListView(data);
                        mView.showContentPage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showErrorPage();
                        mView.showMessage(message);
                        mView.showContentPage();


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

    public void recharge(int type, int payType, int positionId) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("type", type);
            params.put("pay_type", payType);
            params.put("position_id", positionId);
            model.recharge(params, new HttpResultListener<PayInfo>() {
                @Override
                public void onSuccess(PayInfo data) {
                    try {
                        mView.getPayInfoSuccess(data);
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
