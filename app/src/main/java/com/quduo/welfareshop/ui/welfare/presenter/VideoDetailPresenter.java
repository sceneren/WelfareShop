package com.quduo.welfareshop.ui.welfare.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.model.VideoDetailModel;
import com.quduo.welfareshop.ui.welfare.view.IVideoDetailView;

/**
 * Author:scene
 * Time:2018/2/27 12:13
 * Description:视频详情
 */

public class VideoDetailPresenter extends BasePresenter<IVideoDetailView> {
    private VideoDetailModel model;

    public VideoDetailPresenter(IVideoDetailView view) {
        super(view);
        this.mView = view;
        this.model = new VideoDetailModel();
    }
}
