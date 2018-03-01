package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.model.MyFollowNovelModel;
import com.quduo.welfareshop.ui.mine.view.IMyFollowNovelView;

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
}
