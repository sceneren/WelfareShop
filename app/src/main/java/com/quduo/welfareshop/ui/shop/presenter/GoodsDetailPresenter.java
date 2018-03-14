package com.quduo.welfareshop.ui.shop.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.shop.model.GoodsDetailModel;
import com.quduo.welfareshop.ui.shop.view.IGoodsDetailView;

/**
 * Author:scene
 * Time:2018/2/27 15:14
 * Description:商品详情
 */

public class GoodsDetailPresenter extends BasePresenter<IGoodsDetailView> {
    private GoodsDetailModel model;

    public GoodsDetailPresenter(IGoodsDetailView view) {
        super(view);
        this.model = new GoodsDetailModel();
    }
}
