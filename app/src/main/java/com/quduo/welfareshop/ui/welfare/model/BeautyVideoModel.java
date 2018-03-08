package com.quduo.welfareshop.ui.welfare.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.welfare.entity.BeautyVideoResultInfo;

/**
 * Author:scene
 * Time:2018/2/23 15:03
 * Description:美女视频
 */
public class BeautyVideoModel {

    public void getBeautyVideoData(final HttpResultListener<BeautyVideoResultInfo> listener) {
        OkGo.<LzyResponse<BeautyVideoResultInfo>>get(ApiUtil.API_PRE + ApiUtil.BEAUTY_VIDEO)
                .tag(ApiUtil.BEAUTY_VIDEO_TAG)
                .execute(new JsonCallback<LzyResponse<BeautyVideoResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<BeautyVideoResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败，请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<BeautyVideoResultInfo>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        try {
                            listener.onFinish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
