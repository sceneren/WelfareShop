package com.quduo.welfareshop.ui.welfare.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.welfare.entity.VideoDetailInfo;

/**
 * Author:scene
 * Time:2018/2/27 12:12
 * Description:视频详情
 */

public class VideoDetailModel extends ZanModel {
    public void getData(HttpParams params, final HttpResultListener<VideoDetailInfo> listener) {
        OkGo.<LzyResponse<VideoDetailInfo>>get(ApiUtil.API_PRE + ApiUtil.VIDEO_DETAIL)
                .tag(ApiUtil.VIDEO_DETAIL_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<VideoDetailInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<VideoDetailInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<VideoDetailInfo>> response) {
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
