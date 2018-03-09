package com.quduo.welfareshop.ui.welfare.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.entity.GalleryResultInfo;
import com.quduo.welfareshop.ui.welfare.model.GalleryModel;
import com.quduo.welfareshop.ui.welfare.view.IGalleryView;

/**
 * Author:scene
 * Time:2018/1/25  12:05
 * Description:图库
 */
public class GalleryPresenter extends BasePresenter<IGalleryView> {
    private GalleryModel model;

    public GalleryPresenter(IGalleryView view) {
        this.mView = view;
        this.model = new GalleryModel();
    }

    public void getGalleryData(int page, final boolean isFirst) {
        try {
            HttpParams params = new HttpParams();
            params.put("page", page);
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getGalleryData(params, new HttpResultListener<GalleryResultInfo>() {

                @Override
                public void onSuccess(GalleryResultInfo data) {
                    try {
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            mView.refreshFinish();
                            mView.loadmoreFinish();
                        }
                        mView.bindData(data);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        if (isFirst) {
                            mView.showErrorPage();
                        } else {
                            mView.refreshFinish();
                            mView.loadmoreFinish();
                        }
                        mView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
