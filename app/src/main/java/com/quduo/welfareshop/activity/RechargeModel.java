package com.quduo.welfareshop.activity;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.bean.RechargeInfo;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;

/**
 * Author:scene
 * Time:2018/3/5 17:50
 * Description:充值
 */

public class RechargeModel {
    public void getData(final HttpResultListener<RechargeInfo> listener) {
        OkGo.<LzyResponse<RechargeInfo>>get(ApiUtil.API_PRE + ApiUtil.RECHARGE_INDEX)
                .tag(ApiUtil.RECHARGE_INDEX_TAG)
                .execute(new JsonCallback<LzyResponse<RechargeInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<RechargeInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<RechargeInfo>> response) {
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
