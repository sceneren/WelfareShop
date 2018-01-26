package com.quduo.welfareshop.ui.encyc.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.encyc.view.IEncycView;

/**
 * Author:scene
 * Time:2018/1/25  11:17
 * Description:百科主界面
 */
public class EncycPresenter extends BasePresenter<IEncycView> {

    public EncycPresenter(IEncycView view) {
        this.mView = view;
    }
}
