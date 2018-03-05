package com.quduo.welfareshop.ui.welfare.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.model.VideoListModel;
import com.quduo.welfareshop.ui.welfare.view.IVideoListView;

/**
 * Author:scene
 * Time:2018/3/5 17:07
 * Description:视频列表
 */

public class VideoListPresenter extends BasePresenter<IVideoListView> {
    private VideoListModel model;

    public VideoListPresenter(IVideoListView view) {
        this.mView = view;
        this.model = new VideoListModel();
    }

}
