package com.quduo.welfareshop.ui.shop.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.shop.entity.SingleGoodsDetailResultInfo;
import com.quduo.welfareshop.ui.welfare.model.FollowModel;

public class SingleGoodsDetailModel extends FollowModel {
    public void getData(final HttpResultListener<SingleGoodsDetailResultInfo> listener) {
        OkGo.<LzyResponse<SingleGoodsDetailResultInfo>>get(ApiUtil.API_PRE + ApiUtil.VR_DETAIL)
                .tag(ApiUtil.VR_DETAIL_TAG)
                .execute(new JsonCallback<LzyResponse<SingleGoodsDetailResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<SingleGoodsDetailResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<SingleGoodsDetailResultInfo>> response) {
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
