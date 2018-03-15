package com.quduo.welfareshop.ui.friend.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.friend.entity.FollowUserInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/1 17:05
 * Description:我的关注
 */

public class FollowModel {
    public void getData(final HttpResultListener<List<FollowUserInfo>> listener) {
        OkGo.<LzyResponse<List<FollowUserInfo>>>get(ApiUtil.API_PRE + ApiUtil.FOLLOW_USER_LIST)
                .tag(ApiUtil.FOLLOW_USER_LIST_TAG)
                .execute(new JsonCallback<LzyResponse<List<FollowUserInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<FollowUserInfo>>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<FollowUserInfo>>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败，请重试");
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
