package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.entity.MyFollowNovelInfo;
import com.quduo.welfareshop.ui.mine.model.MyFollowNovelModel;
import com.quduo.welfareshop.ui.mine.view.IMyFollowNovelView;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 11:11
 * Description:我的收藏 图片
 */

public class MyFollowNovelPresenter extends BasePresenter<IMyFollowNovelView> {
    private MyFollowNovelModel model;

    public MyFollowNovelPresenter(IMyFollowNovelView view) {
        this.mView = view;
        this.model = new MyFollowNovelModel();
    }

    public void getMyFollowNovelData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getMyFollowNovelData(new HttpResultListener<List<MyFollowNovelInfo>>() {
                @Override
                public void onSuccess(List<MyFollowNovelInfo> data) {
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
