package com.quduo.welfareshop.ui.shop.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.shop.model.CateModel;
import com.quduo.welfareshop.ui.shop.view.ICateView;

/**
 * Author:scene
 * Time:2018/3/22 14:28
 * Description:商品分类
 */

public class CatePresenter extends BasePresenter<ICateView> {
    private CateModel model;

    public CatePresenter(ICateView view) {
        super(view);
        this.model = new CateModel();
    }
}
