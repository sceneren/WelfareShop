package com.quduo.welfareshop.ui.friend.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.friend.entity.InteractResultInfo;
import com.quduo.welfareshop.ui.welfare.model.ZanModel;

public class InteractModel extends ZanModel {
    public void getData(HttpParams params, final HttpResultListener<InteractResultInfo> listener) {
        OkGo.<LzyResponse<InteractResultInfo>>get(ApiUtil.API_PRE + ApiUtil.FRIEND_INTERACT)
                .tag(ApiUtil.FRIEND_INTERACT_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<InteractResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<InteractResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<InteractResultInfo>> response) {
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
