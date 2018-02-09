package com.quduo.welfareshop.ui.welfare.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.model.SmallVideoModel;
import com.quduo.welfareshop.ui.welfare.view.ISmallVideoView;

/**
 * Author:scene
 * Time:2018/2/9 9:48
 * Description:小视频
 */
public class SmallVideoPresenter extends BasePresenter<ISmallVideoView> {
    private SmallVideoModel model;

    public SmallVideoPresenter(ISmallVideoView view) {
        this.mView = view;
        this.model = new SmallVideoModel();
    }
}
