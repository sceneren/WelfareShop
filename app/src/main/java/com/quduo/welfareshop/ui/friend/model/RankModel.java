package com.quduo.welfareshop.ui.friend.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.friend.entity.OtherSimpleUserInfo;
import com.quduo.welfareshop.ui.welfare.entity.FollowSuccessInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/1 17:04
 * Description:人气榜
 */

public class RankModel {

    public void getData(final HttpResultListener<List<OtherSimpleUserInfo>> listener) {
        OkGo.<LzyResponse<List<OtherSimpleUserInfo>>>get(ApiUtil.API_PRE + ApiUtil.RANK_LIST)
                .tag(ApiUtil.RANK_LIST_TAG)
                .execute(new JsonCallback<LzyResponse<List<OtherSimpleUserInfo>>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<OtherSimpleUserInfo>>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<List<OtherSimpleUserInfo>>> response) {
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

    public void followUser(HttpParams params, final HttpResultListener<FollowSuccessInfo> listener) {
        OkGo.<LzyResponse<FollowSuccessInfo>>get(ApiUtil.API_PRE + ApiUtil.FOLLOW_USER)
                .tag(ApiUtil.FOLLOW_USER_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<FollowSuccessInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<FollowSuccessInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<FollowSuccessInfo>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
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
