package com.quduo.welfareshop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.mine.entity.CouponInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 13:47
 * Description:我的代金券
 */

public class MyCouponModel {
    public void getData(final HttpResultListener<List<CouponInfo>> listener) {
        OkGo.<LzyResponse<List<CouponInfo>>>get(ApiUtil.API_PRE + ApiUtil.MY_COUPON)
                .tag(ApiUtil.MY_COUPON_TAG)
                .execute(new JsonCallback<LzyResponse<List<CouponInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<CouponInfo>>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<CouponInfo>>> response) {
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
