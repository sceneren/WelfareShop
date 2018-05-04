package com.quduo.welfareshop.ui.friend.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.ui.friend.entity.HomePageInfo;
import com.quduo.welfareshop.ui.welfare.entity.FollowSuccessInfo;
import com.quduo.welfareshop.ui.welfare.entity.UnlockResultInfo;
import com.quduo.welfareshop.ui.welfare.model.ZanModel;

public class OthersHomePageModel extends ZanModel {
    public void getData(HttpParams params, final HttpResultListener<HomePageInfo> listener) {
        OkGo.<LzyResponse<HomePageInfo>>get(ApiUtil.API_PRE + ApiUtil.OTHERS_HOME_PAGE)
                .tag(ApiUtil.OTHERS_HOME_PAGE_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<HomePageInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<HomePageInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("数据获取失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<HomePageInfo>> response) {
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
                            listener.onFail("关注失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<FollowSuccessInfo>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("关注失败请重试");
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }

    public void cancelFollowUser(HttpParams params, final HttpResultListener<String> listener) {
        OkGo.<LzyResponse<String>>get(ApiUtil.API_PRE + ApiUtil.CENCEL_FOLLOW_USER)
                .tag(ApiUtil.CENCEL_FOLLOW_USER_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<String>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<String>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("取消关注失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<String>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("取消关注失败请重试");
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }

    public void unlockChat(final HttpResultListener<UnlockResultInfo> listener) {
        OkGo.<LzyResponse<UnlockResultInfo>>get(ApiUtil.API_PRE + ApiUtil.UNLOCK_CHAT)
                .tag(ApiUtil.UNLOCK_CHAT_TAG)
                .execute(new JsonCallback<LzyResponse<UnlockResultInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<UnlockResultInfo>> response) {
                        try {
                            listener.onSuccess(response.body().data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("解锁失败请重试");
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<UnlockResultInfo>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("解锁失败请重试");
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
