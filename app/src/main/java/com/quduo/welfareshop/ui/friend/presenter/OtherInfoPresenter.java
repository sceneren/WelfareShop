package com.quduo.welfareshop.ui.friend.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.entity.OtherDetailUserInfo;
import com.quduo.welfareshop.ui.friend.model.OtherInfoModel;
import com.quduo.welfareshop.ui.friend.view.IOtherInfoView;
import com.quduo.welfareshop.ui.welfare.entity.FollowSuccessInfo;

/**
 * Author:scene
 * Time:2018/2/5 14:57
 * Description:别人的主页
 */

public class OtherInfoPresenter extends BasePresenter<IOtherInfoView> {
    private OtherInfoModel model;

    public OtherInfoPresenter(IOtherInfoView view) {
        super(view);
        this.model = new OtherInfoModel();
    }

    public void getData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("target_user_id", mView.getTargetUserId());
            params.put("longitude", mView.getLongitude());
            params.put("latitude", mView.getLatitude());
            params.put("from_nearby", mView.getFromNearby());
            model.getData(params, new HttpResultListener<OtherDetailUserInfo>() {
                @Override
                public void onSuccess(OtherDetailUserInfo data) {
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

    public void followUser() {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("target_user_id", mView.getTargetUserId());
            params.put("from_nearby", mView.getFromNearby());
            params.put("longitude", mView.getLongitude());
            params.put("latitude", mView.getLatitude());
            model.followUser(params, new HttpResultListener<FollowSuccessInfo>() {
                @Override
                public void onSuccess(FollowSuccessInfo data) {
                    try {
                        mView.followSuccess(data.getId());
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

    public void cancelFollowUser() {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("subscribe_id", mView.getFollowId());
            model.cancelFollowUser(params, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    try {
                        mView.cancelFollowSuccess();
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
