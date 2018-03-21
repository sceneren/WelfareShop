package com.quduo.welfareshop.ui.welfare.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.entity.GalleryDetailResultInfo;
import com.quduo.welfareshop.ui.welfare.entity.UnlockResultInfo;
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
        super(view);
        this.model = new GalleryDetailModel();
    }

    public void getGalleryDetailData(int id, final boolean isFirst) {
        try {
            HttpParams params = new HttpParams();
            params.put("id", id);
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getGalleryDetailData(params, new HttpResultListener<GalleryDetailResultInfo>() {

                @Override
                public void onSuccess(GalleryDetailResultInfo data) {
                    try {
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            mView.refreshFinish();
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

    public void unlock() {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("type", "gallery");
            params.put("data_id", mView.getDataId());
            model.unlock(params, new HttpResultListener<UnlockResultInfo>() {
                @Override
                public void onSuccess(UnlockResultInfo data) {
                    try {
                        mView.showMessage("解锁成功");
                        mView.unlockSuccess(data.getScore());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        mView.hideLoadingDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
