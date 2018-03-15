package com.quduo.welfareshop.ui.mine.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;

/**
 * Author:scene
 * Time:2018/2/2 15:46
 * Description:我的资料
 */
public class MyInfoModel {
    public void getData(final HttpResultListener<MyUserDetailInfo> listener) {
        OkGo.<LzyResponse<MyUserDetailInfo>>get(ApiUtil.API_PRE + ApiUtil.MY_DETAIL_INFO)
                .tag(ApiUtil.MY_DETAIL_INFO_TAG)
                .execute(new JsonCallback<LzyResponse<MyUserDetailInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<MyUserDetailInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<MyUserDetailInfo>> response) {
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
