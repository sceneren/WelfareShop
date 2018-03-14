package com.quduo.welfareshop.ui.welfare.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.welfare.entity.GalleryDetailResultInfo;

/**
 * Author:scene
 * Time:2018/3/5 16:54
 * Description:图库详情
 */

public class GalleryDetailModel {
    public void getGalleryDetailData(HttpParams params, final HttpResultListener<GalleryDetailResultInfo> listener) {
        OkGo.<LzyResponse<GalleryDetailResultInfo>>get(ApiUtil.API_PRE + ApiUtil.GALLERY_DETAIL)
                .tag(ApiUtil.GALLERY_DETAIL_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<GalleryDetailResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<GalleryDetailResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<GalleryDetailResultInfo>> response) {
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
