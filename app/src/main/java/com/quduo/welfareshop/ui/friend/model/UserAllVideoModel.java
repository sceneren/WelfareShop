package com.quduo.welfareshop.ui.friend.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.friend.entity.UserVideoInfo;
import com.quduo.welfareshop.ui.welfare.model.UnlockModel;

import java.util.List;

public class UserAllVideoModel extends UnlockModel {
    public void getData(HttpParams params, final HttpResultListener<List<UserVideoInfo>> listener) {
        OkGo.<LzyResponse<List<UserVideoInfo>>>get(ApiUtil.API_PRE + ApiUtil.USER_ALL_VIDEO)
                .tag(ApiUtil.USER_ALL_VIDEO_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<List<UserVideoInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<UserVideoInfo>>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<UserVideoInfo>>> response) {
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
