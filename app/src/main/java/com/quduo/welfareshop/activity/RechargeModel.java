package com.quduo.welfareshop.activity;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.bean.RechargeInfo;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.shop.entity.PayInfo;

/**
 * Author:scene
 * Time:2018/3/5 17:50
 * Description:充值
 */

public class RechargeModel {
    public void getData(final HttpResultListener<RechargeInfo> listener) {
        OkGo.<LzyResponse<RechargeInfo>>get(ApiUtil.API_PRE + ApiUtil.RECHARGE_INDEX)
                .tag(ApiUtil.RECHARGE_INDEX_TAG)
                .execute(new JsonCallback<LzyResponse<RechargeInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<RechargeInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<RechargeInfo>> response) {
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

    public void recharge(HttpParams params, final HttpResultListener<PayInfo> listener) {
        OkGo.<LzyResponse<PayInfo>>post(ApiUtil.API_PRE + ApiUtil.RECHARGE)
                .tag(ApiUtil.RECHARGE_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<PayInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<PayInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("支付信息获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<PayInfo>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("支付信息获取失败请重试");
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
