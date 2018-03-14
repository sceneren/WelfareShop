package com.quduo.welfareshop.ui.welfare.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.entity.FollowSuccessInfo;
import com.quduo.welfareshop.ui.welfare.entity.GalleryDetailResultInfo;
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


    public void followGallery() {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("data_id", mView.getGalleryId());
            model.followGallery(params, new HttpResultListener<FollowSuccessInfo>() {
                @Override
                public void onSuccess(FollowSuccessInfo data) {
                    try {
                        if (data.getId() != 0) {
                            mView.showHasFollow(data.getId());
                        } else {
                            mView.showMessage("操作失败，请重试");
                        }
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

    public void cancelFollow() {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("id", mView.getFollowId());
            model.cancelFollow(params, new HttpResultListener<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    try {
                        if (data) {
                            mView.showNoFollow();
                        } else {
                            mView.showMessage("操作失败，请重试");
                        }
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
