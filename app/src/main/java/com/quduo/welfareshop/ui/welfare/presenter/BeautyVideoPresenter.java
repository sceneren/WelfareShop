package com.quduo.welfareshop.ui.welfare.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.model.BeautyVideoModel;
import com.quduo.welfareshop.ui.welfare.view.IBeautyVideoView;

/**
 * Author:scene
 * Time:2018/2/23 15:03
 * Description:美女视频
 */
public class BeautyVideoPresenter extends BasePresenter<IBeautyVideoView> {
    private BeautyVideoModel model;

    public BeautyVideoPresenter(IBeautyVideoView view) {
        this.mView = view;
        this.model = new BeautyVideoModel();
    }
}
