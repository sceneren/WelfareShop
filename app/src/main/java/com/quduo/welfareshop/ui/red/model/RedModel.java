package com.quduo.welfareshop.ui.red.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.red.entity.BuyRedResultInfo;
import com.quduo.welfareshop.ui.red.entity.OpenRedResultInfo;
import com.quduo.welfareshop.ui.red.entity.RedResultInfo;

/**
 * Author:scene
 * Time:2018/2/1 15:24
 * Description:红包
 */

public class RedModel {
    public void getData(final HttpResultListener<RedResultInfo> listener) {
        OkGo.<LzyResponse<RedResultInfo>>get(ApiUtil.API_PRE + ApiUtil.RED_INDEX)
                .tag(ApiUtil.RED_INDEX_TAG)
                .execute(new JsonCallback<LzyResponse<RedResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<RedResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<RedResultInfo>> response) {
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

    public void buyRed(HttpParams params, final HttpResultListener<BuyRedResultInfo> listener) {
        OkGo.<LzyResponse<BuyRedResultInfo>>post(ApiUtil.API_PRE + ApiUtil.BUY_RED)
                .tag(ApiUtil.BUY_RED_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<BuyRedResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<BuyRedResultInfo>> response) {
                        try {
                            if (response.body().status) {
                                listener.onSuccess(response.body().data);
                            } else {
                                listener.onFail("领取失败请重试");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("领取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<BuyRedResultInfo>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("领取失败请重试");
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
