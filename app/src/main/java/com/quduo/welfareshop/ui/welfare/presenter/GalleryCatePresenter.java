package com.quduo.welfareshop.ui.welfare.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.entity.FollowSuccessInfo;
import com.quduo.welfareshop.ui.welfare.entity.GalleryCateResultInfo;
import com.quduo.welfareshop.ui.welfare.model.GalleryCateModel;
import com.quduo.welfareshop.ui.welfare.view.IGalleryCateView;

/**
 * Author:scene
 * Time:2018/1/25  12:05
 * Description:图库
 */
public class GalleryCatePresenter extends BasePresenter<IGalleryCateView> {
    private GalleryCateModel model;

    public GalleryCatePresenter(IGalleryCateView view) {
        super(view);
        this.model = new GalleryCateModel();
    }

    public void getGalleryData(int page, final boolean isFirst) {
        try {
            HttpParams params = new HttpParams();
            params.put("page", page);
            params.put("cate_id", mView.getCateId());
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getGallertCateData(params, new HttpResultListener<GalleryCateResultInfo>() {

                @Override
                public void onSuccess(GalleryCateResultInfo data) {
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

    public void followGallery(final int position, int id) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("data_id", id);
            model.followGallery(params, new HttpResultListener<FollowSuccessInfo>() {
                @Override
                public void onSuccess(FollowSuccessInfo data) {
                    try {
                        if (data.getId() != 0) {
                            mView.followSuccess(position, data.getId());
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

    public void cancelFollow(final int position, final int followId) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("id", followId);
            model.cancelFollow(params, new HttpResultListener<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    try {
                        if (data) {
                            mView.cancelFollowSuccess(position);
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
