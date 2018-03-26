package com.quduo.welfareshop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.entity.MyFollowGalleryInfo;
import com.quduo.welfareshop.ui.mine.model.MyFollowImageModel;
import com.quduo.welfareshop.ui.mine.view.IMyFollowImageView;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 11:11
 * Description:我的收藏 图片
 */

public class MyFollowImagePresenter extends BasePresenter<IMyFollowImageView> {
    private MyFollowImageModel model;

    public MyFollowImagePresenter(IMyFollowImageView view) {
        super(view);
        this.model = new MyFollowImageModel();
    }

    public void getData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getData(new HttpResultListener<List<MyFollowGalleryInfo>>() {
                @Override
                public void onSuccess(List<MyFollowGalleryInfo> data) {
                    try {
                        mView.bindData(data);
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            mView.refreshFinish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
                        if (isFirst) {
                            mView.showErrorPage();
                        } else {
                            mView.refreshFinish();
                        }
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

    public void cancelFollowImage(final int position, int followId) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("id", followId);
            model.cancelFollow(params, new HttpResultListener<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    try {
                        mView.cancelFollowSuccess(position);
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
    public void zanGallery(final int position, int dataId) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("type", 2);
            params.put("data_id", dataId);
            model.zan(params, new HttpResultListener<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    try {
                        mView.zanSuccess(position);
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
