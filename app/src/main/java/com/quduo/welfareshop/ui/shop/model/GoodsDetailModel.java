package com.quduo.welfareshop.ui.shop.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.shop.entity.GoodsDetailResultInfo;

/**
 * Author:scene
 * Time:2018/2/27 15:14
 * Description:商品详情
 */

public class GoodsDetailModel {
    public void getData(HttpParams params, final HttpResultListener<GoodsDetailResultInfo> listener) {
        OkGo.<LzyResponse<GoodsDetailResultInfo>>get(ApiUtil.API_PRE + ApiUtil.GOODS_DETAIL_TAG)
                .tag(ApiUtil.GOODS_DETAIL_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<GoodsDetailResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<GoodsDetailResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<GoodsDetailResultInfo>> response) {
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
