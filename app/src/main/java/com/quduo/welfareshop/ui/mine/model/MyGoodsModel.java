package com.quduo.welfareshop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.mine.entity.MyFollowGoodsInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 10:38
 * Description:我收藏的商品
 */

public class MyGoodsModel {
    public void getData(final HttpResultListener<List<MyFollowGoodsInfo>> listener) {
        OkGo.<LzyResponse<List<MyFollowGoodsInfo>>>get(ApiUtil.API_PRE + ApiUtil.MY_FOLLOW_GOODS)
                .tag(ApiUtil.MY_FOLLOW_GOODS_TAG)
                .execute(new JsonCallback<LzyResponse<List<MyFollowGoodsInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<MyFollowGoodsInfo>>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<MyFollowGoodsInfo>>> response) {
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
