package com.quduo.welfareshop.ui.friend.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.friend.entity.NearResultInfo;

/**
 * Author:scene
 * Time:2018/2/1 17:04
 * Description:附近的人
 */

public class NearModel {
    public void getData(HttpParams params, final HttpResultListener<NearResultInfo> listener) {
        OkGo.<LzyResponse<NearResultInfo>>get(ApiUtil.API_PRE + ApiUtil.NEAR_V2)
                .tag(ApiUtil.NEAR_V2_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<NearResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<NearResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<NearResultInfo>> response) {
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
}
