package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.view.IMyFollowView;

/**
 * Author:scene
 * Time:2018/3/1 11:11
 * Description:我的收藏
 */

public class MyFollowPresenter extends BasePresenter<IMyFollowView> {
    public MyFollowPresenter(IMyFollowView view) {
        this.mView = view;
    }
}
