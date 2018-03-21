package com.quduo.welfareshop.ui.red.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.red.entity.RedHistoryResultInfo;

/**
 * Author:scene
 * Time:2018/3/8 10:00
 * Description:历史记录
 */

public class HistoryRedModel {
    public void getData(HttpParams params, final HttpResultListener<RedHistoryResultInfo> listener) {
        OkGo.<LzyResponse<RedHistoryResultInfo>>get(ApiUtil.API_PRE + ApiUtil.RED_HISTORY)
                .tag(ApiUtil.RED_HISTORY_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<RedHistoryResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<RedHistoryResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<RedHistoryResultInfo>> response) {
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
