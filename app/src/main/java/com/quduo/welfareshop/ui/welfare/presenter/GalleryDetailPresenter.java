package com.quduo.welfareshop.ui.welfare.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.model.GalleryDetailModel;
import com.quduo.welfareshop.ui.welfare.view.IGalleryDetailView;

/**
 * Author:scene
 * Time:2018/3/5 16:54
 * Description:图库详情
 */

public class GalleryDetailPresenter extends BasePresenter<IGalleryDetailView> {
    private GalleryDetailModel model;

    public GalleryDetailPresenter(IGalleryDetailView view) {
        this.mView = view;
        this.model = new GalleryDetailModel();
    }
}
