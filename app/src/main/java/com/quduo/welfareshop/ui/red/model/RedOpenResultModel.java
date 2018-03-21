package com.quduo.welfareshop.ui.red.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.red.entity.RedOpenResultInfo;

/**
 * Author:scene
 * Time:2018/3/21 12:26
 * Description:开机结果
 */

public class RedOpenResultModel {
    public void getData(HttpParams params, final HttpResultListener<RedOpenResultInfo> listener) {
        OkGo.<LzyResponse<RedOpenResultInfo>>get(ApiUtil.API_PRE + ApiUtil.RED_DETAIL)
                .tag(ApiUtil.RED_DETAIL_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<RedOpenResultInfo>>() {

                    @Override
                    public void onSuccess(Response<LzyResponse<RedOpenResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<RedOpenResultInfo>> response) {
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
