package com.quduo.welfareshop.ui.red.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.red.view.IMyRedView;

/**
 * Author:scene
 * Time:2018/3/8 9:44
 * Description:我的红包--未拆开和历史记录
 */

public class MyRedPresenter extends BasePresenter<IMyRedView> {
    public MyRedPresenter(IMyRedView view) {
        this.mView = view;
    }
}
