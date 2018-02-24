package com.quduo.welfareshop.ui.welfare.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.model.MidNightVideoModel;
import com.quduo.welfareshop.ui.welfare.view.IMidNightVideoView;

/**
 * Author:scene
 * Time:2018/2/24 10:09
 * Description:午夜影院
 */
public class MidNightVideoPresenter extends BasePresenter<IMidNightVideoView> {
    private MidNightVideoModel model;

    public MidNightVideoPresenter(IMidNightVideoView view) {
        this.mView = view;
        this.model = new MidNightVideoModel();
    }
}
