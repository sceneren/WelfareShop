package com.quduo.welfareshop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.mine.entity.ReceiverInfo;

/**
 * Author:scene
 * Time:2018/3/1 14:08
 * Description:收货信息
 */

public class MyReceiverModel {
    public void getDada(final HttpResultListener<ReceiverInfo> listener) {
        OkGo.<LzyResponse<ReceiverInfo>>get(ApiUtil.API_PRE + ApiUtil.RECEIVER_ADDRESS)
                .tag(ApiUtil.RECEIVER_ADDRESS_TAG)
                .execute(new JsonCallback<LzyResponse<ReceiverInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<ReceiverInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<ReceiverInfo>> response) {
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

    public void updateReceiverInfo(HttpParams params, final HttpResultListener<Boolean> listener) {
        OkGo.<LzyResponse<Boolean>>post(ApiUtil.API_PRE + ApiUtil.EDIT_RECEIVER_ADDRESS)
                .tag(ApiUtil.EDIT_RECEIVER_ADDRESS_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<Boolean>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<Boolean>> response) {
                        try {
                            if (response.body().status) {
                                listener.onSuccess(true);
                            } else {
                                listener.onFail("操作失败请重试");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("操作失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<Boolean>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("操作失败请重试");
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
