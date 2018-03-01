package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.model.MyFollowVideoModel;
import com.quduo.welfareshop.ui.mine.view.IMyFollowVideoView;

/**
 * Author:scene
 * Time:2018/3/1 11:11
 * Description:我的收藏 视频
 */

public class MyFollowVideoPresenter extends BasePresenter<IMyFollowVideoView> {
    private MyFollowVideoModel model;

    public MyFollowVideoPresenter(IMyFollowVideoView view) {
        this.mView = view;
        this.model = new MyFollowVideoModel();
    }
}
