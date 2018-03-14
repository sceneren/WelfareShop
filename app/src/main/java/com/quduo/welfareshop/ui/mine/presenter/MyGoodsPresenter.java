package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.model.MyGoodsModel;
import com.quduo.welfareshop.ui.mine.view.IMyGoodsView;

/**
 * Author:scene
 * Time:2018/3/1 10:38
 * Description:我收藏的商品
 */

public class MyGoodsPresenter extends BasePresenter<IMyGoodsView> {
    private MyGoodsModel model;

    public MyGoodsPresenter(IMyGoodsView view) {
        super(view);
        this.model = new MyGoodsModel();
    }
}
