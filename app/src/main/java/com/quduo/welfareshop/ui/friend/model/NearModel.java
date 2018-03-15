package com.quduo.welfareshop.ui.friend.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.friend.entity.NearInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/1 17:04
 * Description:附近的人
 */

public class NearModel {
    public void getData(HttpParams params, final HttpResultListener<List<NearInfo>> listener) {
        OkGo.<LzyResponse<List<NearInfo>>>get(ApiUtil.API_PRE + ApiUtil.NEAR_LIST)
                .tag(ApiUtil.NEAR_LIST_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<List<NearInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<NearInfo>>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<NearInfo>>> response) {
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
