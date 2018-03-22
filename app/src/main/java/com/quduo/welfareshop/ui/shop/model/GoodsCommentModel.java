package com.quduo.welfareshop.ui.shop.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.shop.entity.GoodsCommentResultInfo;

/**
 * Author:scene
 * Time:2018/2/28 15:41
 * Description:买家秀
 */

public class GoodsCommentModel {
    public void getData(HttpParams params, final HttpResultListener<GoodsCommentResultInfo> listener) {
        OkGo.<LzyResponse<GoodsCommentResultInfo>>get(ApiUtil.API_PRE + ApiUtil.GOODS_COMMENT)
                .tag(ApiUtil.GOODS_COMMENT_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<GoodsCommentResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<GoodsCommentResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<GoodsCommentResultInfo>> response) {
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
