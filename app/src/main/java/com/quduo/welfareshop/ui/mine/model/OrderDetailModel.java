package com.quduo.welfareshop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.mine.entity.OrderDetailResultInfo;
import com.quduo.welfareshop.ui.shop.entity.PayInfo;

/**
 * Author:scene
 * Time:2018/3/14 10:13
 * Description:订单详情
 * 1未支付  2已支付  3已发货  4已评价  5已取消
 */

public class OrderDetailModel {
    public void getData(HttpParams params, final HttpResultListener<OrderDetailResultInfo> listener) {
        OkGo.<LzyResponse<OrderDetailResultInfo>>get(ApiUtil.API_PRE + ApiUtil.ORDER_DETAIL)
                .tag(ApiUtil.ORDER_DETAIL_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<OrderDetailResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<OrderDetailResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<OrderDetailResultInfo>> response) {
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

    //重新发起支付
    public void rePay(HttpParams params, final HttpResultListener<PayInfo> listener) {
        OkGo.<LzyResponse<PayInfo>>post(ApiUtil.API_PRE + ApiUtil.REPAY_ORDER)
                .tag(ApiUtil.REPAY_ORDER_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<PayInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<PayInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("支付信息获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<PayInfo>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("支付信息获取失败请重试");
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
