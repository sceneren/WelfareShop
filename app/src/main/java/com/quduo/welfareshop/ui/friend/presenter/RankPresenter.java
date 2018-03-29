package com.quduo.welfareshop.ui.friend.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.entity.OtherSimpleUserInfo;
import com.quduo.welfareshop.ui.friend.model.RankModel;
import com.quduo.welfareshop.ui.friend.view.IRankView;
import com.quduo.welfareshop.ui.welfare.entity.FollowSuccessInfo;
import com.quduo.welfareshop.ui.welfare.entity.UnlockResultInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/1 17:10
 * Description:人气榜
 */

public class RankPresenter extends BasePresenter<IRankView> {
    private RankModel model;

    public RankPresenter(IRankView view) {
        super(view);
        this.model = new RankModel();
    }

    public void getData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getData(new HttpResultListener<List<OtherSimpleUserInfo>>() {
                @Override
                public void onSuccess(List<OtherSimpleUserInfo> data) {
                    try {
                        mView.bindData(data);
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            mView.refreshFinish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
                        if (isFirst) {
                            mView.showErrorPage();
                        } else {
                            mView.refreshFinish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void followUser(final int position, int targetUserId) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("target_user_id", targetUserId);
            params.put("from_nearby", 0);
            params.put("longitude", mView.getLongitude());
            params.put("latitude", mView.getLatitude());
            model.followUser(params, new HttpResultListener<FollowSuccessInfo>() {
                @Override
                public void onSuccess(FollowSuccessInfo data) {
                    try {
                        mView.followSuccess(position, data.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        mView.hideLoadingDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void cancelFollowUser(final int position, int subscribeId) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("subscribe_id", subscribeId);
            model.cancelFollowUser(params, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    try {
                        mView.cancelFollowSuccess(position);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        mView.hideLoadingDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
