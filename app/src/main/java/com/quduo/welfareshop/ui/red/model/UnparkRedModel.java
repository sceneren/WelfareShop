package com.quduo.welfareshop.ui.red.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.red.entity.OpenRedResultInfo;
import com.quduo.welfareshop.ui.red.entity.UnparkRedInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/8 9:59
 * Description:未拆开的红包
 */

public class UnparkRedModel {
    public void getData(final HttpResultListener<List<UnparkRedInfo>> listener) {
        OkGo.<LzyResponse<List<UnparkRedInfo>>>get(ApiUtil.API_PRE + ApiUtil.UNPARK_RED)
                .tag(ApiUtil.UNPARK_RED_TAG)
                .execute(new JsonCallback<LzyResponse<List<UnparkRedInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<UnparkRedInfo>>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<UnparkRedInfo>>> response) {
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

    public void openRed(HttpParams params, final HttpResultListener<OpenRedResultInfo> listener) {
        OkGo.<LzyResponse<OpenRedResultInfo>>get(ApiUtil.API_PRE + ApiUtil.OPEN_RED)
                .tag(ApiUtil.OPEN_RED_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<OpenRedResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<OpenRedResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("操作失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<OpenRedResultInfo>> response) {
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
