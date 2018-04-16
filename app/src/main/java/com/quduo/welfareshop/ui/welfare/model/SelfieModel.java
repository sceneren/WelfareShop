package com.quduo.welfareshop.ui.welfare.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.welfare.entity.SmallVideoResultInfo;

public class SelfieModel extends ZanModel{
    public void getData(HttpParams params, final HttpResultListener<SmallVideoResultInfo> listener) {
        OkGo.<LzyResponse<SmallVideoResultInfo>>get(ApiUtil.API_PRE + ApiUtil.SELFIE_VIDEO)
                .tag(ApiUtil.SELFIE_VIDEO_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<SmallVideoResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<SmallVideoResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败，请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<SmallVideoResultInfo>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                });
    }
}
