package com.quduo.welfareshop.ui.friend.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.model.UserAllVideoModel;
import com.quduo.welfareshop.ui.friend.view.IUserAllVideoView;

public class UserAllViewPresenter extends BasePresenter<IUserAllVideoView> {
    private UserAllVideoModel model;

    public UserAllViewPresenter(IUserAllVideoView view) {
        super(view);
        this.model = new UserAllVideoModel();
    }

    public void unlock() {
        
    }
}
