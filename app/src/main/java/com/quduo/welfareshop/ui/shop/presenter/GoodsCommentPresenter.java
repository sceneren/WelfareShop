package com.quduo.welfareshop.ui.shop.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.shop.model.GoodsCommentModel;
import com.quduo.welfareshop.ui.shop.view.IGoodsCommentView;

/**
 * Author:scene
 * Time:2018/2/28 15:42
 * Description:买家秀
 */

public class GoodsCommentPresenter extends BasePresenter<IGoodsCommentView> {
    private GoodsCommentModel model;

    public GoodsCommentPresenter(IGoodsCommentView view) {
        this.mView = view;
        this.model = new GoodsCommentModel();
    }
}
