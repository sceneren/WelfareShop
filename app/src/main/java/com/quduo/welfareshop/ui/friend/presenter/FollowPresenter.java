package com.quduo.welfareshop.ui.friend.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.model.FollowModel;
import com.quduo.welfareshop.ui.friend.view.IFollowView;

/**
 * Author:scene
 * Time:2018/2/1 17:08
 * Description:关注
 */

public class FollowPresenter extends BasePresenter<IFollowView> {
    private FollowModel model;

    public FollowPresenter(IFollowView view) {
        this.mView = view;
        this.model = new FollowModel();
    }
}
