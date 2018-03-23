package com.quduo.welfareshop.ui.shop.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.shop.entity.ConfirmOrderResultInfo;

/**
 * Author:scene
 * Time:2018/3/13 15:35
 * Description:确认订单
 */

public class ConfirmOrderModel {
    public void getData(final HttpResultListener<ConfirmOrderResultInfo> listener) {
        OkGo.<LzyResponse<ConfirmOrderResultInfo>>get(ApiUtil.API_PRE + ApiUtil.ORDER_USER_INFO)
                .tag(ApiUtil.ORDER_USER_INFO_TAG)
                .execute(new JsonCallback<LzyResponse<ConfirmOrderResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<ConfirmOrderResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<ConfirmOrderResultInfo>> response) {
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
