package com.quduo.welfareshop.ui.welfare.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.welfare.entity.LsjInfo;
import com.quduo.welfareshop.ui.welfare.entity.UnlockResultInfo;

public class LsjModel {
    public void getData(final HttpResultListener<LsjInfo> listener) {
        OkGo.<LzyResponse<LsjInfo>>get(ApiUtil.API_PRE + ApiUtil.TECH)
                .tag(ApiUtil.TECH_TAG)
                .execute(new JsonCallback<LzyResponse<LsjInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<LsjInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<LsjInfo>> response) {
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

    public void unlock(final HttpResultListener<UnlockResultInfo> listener){
        OkGo.<LzyResponse<UnlockResultInfo>>get(ApiUtil.API_PRE + ApiUtil.UNLOCK_TECH)
                .tag(ApiUtil.UNLOCK_TECH_TAG)
                .execute(new JsonCallback<LzyResponse<UnlockResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<UnlockResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("解锁失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<UnlockResultInfo>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("解锁失败请重试");
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
