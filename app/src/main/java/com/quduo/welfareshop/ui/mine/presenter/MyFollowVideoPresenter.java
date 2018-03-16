package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.entity.MyFollowVideoInfo;
import com.quduo.welfareshop.ui.mine.model.MyFollowVideoModel;
import com.quduo.welfareshop.ui.mine.view.IMyFollowVideoView;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 11:11
 * Description:我的收藏 视频
 */

public class MyFollowVideoPresenter extends BasePresenter<IMyFollowVideoView> {
    private MyFollowVideoModel model;

    public MyFollowVideoPresenter(IMyFollowVideoView view) {
        super(view);
        this.model = new MyFollowVideoModel();
    }

    public void getData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getData(new HttpResultListener<List<MyFollowVideoInfo>>() {
                @Override
                public void onSuccess(List<MyFollowVideoInfo> data) {
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
}
