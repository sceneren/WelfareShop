package com.quduo.welfareshop.ui.friend.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.model.NearModel;
import com.quduo.welfareshop.ui.friend.view.INearView;

/**
 * Author:scene
 * Time:2018/2/1 17:09
 * Description:附近的人
 */

public class NearPresenter extends BasePresenter<INearView> {
    private NearModel model;

    public NearPresenter(INearView view) {
        super(view);
        this.model = new NearModel();
    }
}
