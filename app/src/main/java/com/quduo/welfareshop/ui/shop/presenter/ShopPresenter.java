package com.quduo.welfareshop.ui.shop.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.shop.view.IShopView;
import com.quduo.welfareshop.ui.welfare.view.IWelfareView;

/**
 * Author:scene
 * Time:2018/1/25  11:17
 * Description:商城主界面
 */
public class ShopPresenter extends BasePresenter<IShopView> {

    public ShopPresenter(IShopView view) {
        super(view);
    }
}
