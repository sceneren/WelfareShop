package com.quduo.welfareshop.ui.shop.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.mine.entity.CheckPayResultInfo;
import com.quduo.welfareshop.ui.shop.entity.ConfirmOrderResultInfo;
import com.quduo.welfareshop.ui.shop.entity.PayInfo;

/**
 * Author:scene
 * Time:2018/3/13 15:35
 * Description:确认订单
 */

public class ConfirmOrderModel {
    public void getData(final HttpResultListener<ConfirmOrderResultInfo> listener) {
        OkGo.<LzyResponse<ConfirmOrderResultInfo>>get(ApiUtil.API_PRE + ApiUtil.ORDER_USER_INFO)
                .tag(ApiUtil.ORDER_USER_INFO_TAG)
                .execute(new JsonCallback<LzyResponse<ConfirmOrderResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<ConfirmOrderResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<ConfirmOrderResultInfo>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }

    public void createOrder(HttpParams params, final HttpResultListener<PayInfo> listener) {
        OkGo.<LzyResponse<PayInfo>>post(ApiUtil.API_PRE + ApiUtil.CREATE_ORDER)
                .tag(ApiUtil.CREATE_ORDER_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<PayInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<PayInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("下单失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<PayInfo>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("下单失败请重试");
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }

    public void checkPaySuccess(HttpParams params, final HttpResultListener<CheckPayResultInfo> listener) {
        OkGo.<LzyResponse<CheckPayResultInfo>>get(ApiUtil.API_PRE + ApiUtil.CHECK_PAY_SUCCESS)
                .tag(ApiUtil.CHECK_PAY_SUCCESS_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<CheckPayResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<CheckPayResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("支付失败请重试,如已支付请重启应用或联系客服");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<CheckPayResultInfo>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("支付失败请重试,如已支付请重启应用或联系客服");
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }
}
