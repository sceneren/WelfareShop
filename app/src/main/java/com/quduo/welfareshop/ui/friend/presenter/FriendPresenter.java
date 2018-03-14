package com.quduo.welfareshop.ui.friend.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.model.FriendModel;
import com.quduo.welfareshop.ui.friend.view.IFriendView;

/**
 * Author:scene
 * Time:2018/2/1 15:16
 * Description:
 */

public class FriendPresenter extends BasePresenter<IFriendView> {
    private FriendModel model;

    public FriendPresenter(IFriendView view) {
        super(view);
        model = new FriendModel();
    }
}
