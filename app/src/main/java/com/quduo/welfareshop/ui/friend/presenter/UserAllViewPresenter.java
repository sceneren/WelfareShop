package com.quduo.welfareshop.ui.friend.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.entity.UserVideoInfo;
import com.quduo.welfareshop.ui.friend.model.UserAllVideoModel;
import com.quduo.welfareshop.ui.friend.view.IUserAllVideoView;
import com.quduo.welfareshop.ui.welfare.entity.UnlockResultInfo;

import java.util.List;

public class UserAllViewPresenter extends BasePresenter<IUserAllVideoView> {
    private UserAllVideoModel model;

    public UserAllViewPresenter(IUserAllVideoView view) {
        super(view);
        this.model = new UserAllVideoModel();
    }

    public void getData(int targetUserId, final boolean isFirst) {
        try {
            HttpParams params = new HttpParams();
            params.put("target_user_id", targetUserId);
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getData(params, new HttpResultListener<List<UserVideoInfo>>() {
                @Override
                public void onSuccess(List<UserVideoInfo> data) {
                    try {
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            mView.refreshFinish();
                        }
                        mView.bindData(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        if (isFirst) {
                            mView.showErrorPage();
                        } else {
                            mView.refreshFinish();
                        }
                        mView.showMessage(message);
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

    public void unlockVideo(final int position, int dataId) {
        try {
            mView.hideLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("data_id", dataId);
            params.put("type", "user_video");
            model.unlock(params, new HttpResultListener<UnlockResultInfo>() {
                @Override
                public void onSuccess(UnlockResultInfo data) {
                    try {
                        mView.showMessage("解锁成功");
                        mView.unlockSuccess(position, data.getScore());
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
