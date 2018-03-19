package com.quduo.welfareshop.ui.welfare.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.welfare.entity.MidNightVideoResultInfo;

/**
 * Author:scene
 * Time:2018/2/24 10:09
 * Description:午夜影院
 */
public class MidNightVideoModel {
    public void getMidNightVideoData( final HttpResultListener<MidNightVideoResultInfo> listener) {
        OkGo.<LzyResponse<MidNightVideoResultInfo>>get(ApiUtil.API_PRE + ApiUtil.MIDNIGHT_VIDEO)
                .tag(ApiUtil.MIDNIGHT_VIDEO_TAG)
                .execute(new JsonCallback<LzyResponse<MidNightVideoResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<MidNightVideoResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败，请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<MidNightVideoResultInfo>> response) {
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
