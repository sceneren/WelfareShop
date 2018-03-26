package com.quduo.welfareshop.ui.welfare.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.welfare.entity.GalleryCateResultInfo;

/**
 * Author:scene
 * Time:2018/3/9 13:40
 * Description:图库分类
 */

public class GalleryCateModel extends ZanModel {
    public void getGallertCateData(HttpParams params, final HttpResultListener<GalleryCateResultInfo> listener) {
        OkGo.<LzyResponse<GalleryCateResultInfo>>get(ApiUtil.API_PRE + ApiUtil.GALLERY_CATE)
                .tag(ApiUtil.GALLERY_CATE_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<GalleryCateResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<GalleryCateResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败，请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<GalleryCateResultInfo>> response) {
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
                        listener.onFinish();
                    }
                });
    }

}
