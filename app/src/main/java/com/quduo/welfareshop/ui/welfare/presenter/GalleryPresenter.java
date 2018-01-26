package com.quduo.welfareshop.ui.welfare.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.view.IGalleryView;

/**
 * Author:scene
 * Time:2018/1/25  12:05
 * Description:图库
 */
public class GalleryPresenter extends BasePresenter<IGalleryView> {
    public GalleryPresenter(IGalleryView view) {
        this.mView = view;
    }
}
