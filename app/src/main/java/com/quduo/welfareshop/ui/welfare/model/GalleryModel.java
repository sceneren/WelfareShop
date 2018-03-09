package com.quduo.welfareshop.ui.welfare.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.welfare.entity.GalleryResultInfo;

/**
 * Author:scene
 * Time:2018/3/5 16:54
 * Description:图库
 */

public class GalleryModel {

    public void getGalleryData(HttpParams params, final HttpResultListener<GalleryResultInfo> listener) {
        OkGo.<LzyResponse<GalleryResultInfo>>get(ApiUtil.API_PRE + ApiUtil.GALLERY)
                .tag(ApiUtil.GALLERY_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<GalleryResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<GalleryResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败，请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<GalleryResultInfo>> response) {
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
