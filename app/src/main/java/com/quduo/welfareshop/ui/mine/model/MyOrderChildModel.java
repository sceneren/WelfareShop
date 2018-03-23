package com.quduo.welfareshop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.mine.entity.OrderListResultInfo;

/**
 * Author:scene
 * Time:2018/2/28 18:02
 * Description:订单子页面
 */

public class MyOrderChildModel {
    public void getData(HttpParams params, final HttpResultListener<OrderListResultInfo> listener) {
        OkGo.<LzyResponse<OrderListResultInfo>>get(ApiUtil.API_PRE + ApiUtil.ORDER_LIST)
                .tag(ApiUtil.ORDER_LIST_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<OrderListResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<OrderListResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<OrderListResultInfo>> response) {
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
