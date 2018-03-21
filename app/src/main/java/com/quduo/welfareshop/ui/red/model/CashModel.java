package com.quduo.welfareshop.ui.red.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.red.entity.CashResultInfo;

/**
 * Author:scene
 * Time:2018/3/8 11:27
 * Description:提现
 */

public class CashModel {
    public void cash(HttpParams params, final HttpResultListener<Boolean> listener) {
        OkGo.<LzyResponse<Boolean>>post(ApiUtil.API_PRE + ApiUtil.CASH)
                .tag(ApiUtil.CASH_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<Boolean>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<Boolean>> response) {
                        try {
                            listener.onSuccess(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("提现失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<Boolean>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("提现失败请重试");
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
