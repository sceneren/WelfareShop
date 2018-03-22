package com.quduo.welfareshop.ui.shop.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.shop.entity.ShopDataInfo;

/**
 * Author:scene
 * Time:2018/3/22 14:28
 * Description:商品分类
 */

public class CateModel {
    public void getData(HttpParams params, final HttpResultListener<ShopDataInfo> listener) {
        OkGo.<LzyResponse<ShopDataInfo>>get(ApiUtil.API_PRE + ApiUtil.SHOP_CATE_LIST)
                .tag(ApiUtil.SHOP_CATE_LIST_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<ShopDataInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<ShopDataInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<ShopDataInfo>> response) {
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
