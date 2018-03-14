package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.model.MyCouponModel;
import com.quduo.welfareshop.ui.mine.view.IMyCouponView;

/**
 * Author:scene
 * Time:2018/3/1 13:47
 * Description:我的代金券
 */

public class MyCouponPresenter extends BasePresenter<IMyCouponView> {
    private MyCouponModel model;

    public MyCouponPresenter(IMyCouponView view) {
        super(view);
        this.model = new MyCouponModel();
    }
}
