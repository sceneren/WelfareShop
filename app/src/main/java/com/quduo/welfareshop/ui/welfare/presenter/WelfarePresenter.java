package com.quduo.welfareshop.ui.welfare.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.view.IWelfareView;

/**
 * Author:scene
 * Time:2018/1/25  11:17
 * Description:福利主界面
 */
public class WelfarePresenter extends BasePresenter<IWelfareView> {

    public WelfarePresenter(IWelfareView view) {
        super(view);
        this.mView = view;
    }
}
