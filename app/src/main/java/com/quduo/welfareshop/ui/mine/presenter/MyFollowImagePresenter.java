package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.model.MyFollowImageModel;
import com.quduo.welfareshop.ui.mine.view.IMyFollowImageView;

/**
 * Author:scene
 * Time:2018/3/1 11:11
 * Description:我的收藏 图片
 */

public class MyFollowImagePresenter extends BasePresenter<IMyFollowImageView> {
    private MyFollowImageModel model;

    public MyFollowImagePresenter(IMyFollowImageView view) {
        super(view);
        this.model = new MyFollowImageModel();
    }
}
