package com.quduo.welfareshop.ui.friend.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.model.FriendVideoDetailModel;
import com.quduo.welfareshop.ui.friend.view.IFriendVideoDetailView;
import com.quduo.welfareshop.ui.welfare.entity.UnlockResultInfo;

public class FriendVideoDetailPresenter extends BasePresenter<IFriendVideoDetailView> {
    private FriendVideoDetailModel model;

    public FriendVideoDetailPresenter(IFriendVideoDetailView view) {
        super(view);
        this.model = new FriendVideoDetailModel();
    }

    public void unlockVideo(int dataId) {
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
                        mView.unlockSuccess(data.getScore());
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
