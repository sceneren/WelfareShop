package com.quduo.welfareshop.ui.welfare.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.entity.FollowSuccessInfo;
import com.quduo.welfareshop.ui.welfare.entity.SmallVideoResultInfo;
import com.quduo.welfareshop.ui.welfare.entity.UnlockResultInfo;
import com.quduo.welfareshop.ui.welfare.model.SmallVideoModel;
import com.quduo.welfareshop.ui.welfare.view.ISmallVideoView;

/**
 * Author:scene
 * Time:2018/2/9 9:48
 * Description:小视频
 */
public class SmallVideoPresenter extends BasePresenter<ISmallVideoView> {
    private SmallVideoModel model;

    public SmallVideoPresenter(ISmallVideoView view) {
        super(view);
        this.mView = view;
        this.model = new SmallVideoModel();
    }

    public void getSmallVideoData(int currentPage, final boolean isFirst) {
        try {
            HttpParams params = new HttpParams();
            params.put("page", currentPage);
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getSmallVideoByPage(params, new HttpResultListener<SmallVideoResultInfo>() {

                @Override
                public void onSuccess(SmallVideoResultInfo data) {
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

    public void followVideo(final int position, final int dataId) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("data_id", dataId);
            model.followVideo(params, new HttpResultListener<FollowSuccessInfo>() {
                @Override
                public void onSuccess(FollowSuccessInfo data) {
                    try {
                        mView.followSuccess(position, data.getId());
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

    public void cancelFollow(final int position, int followId) {
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

    public void zan(final int position, int dataId) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("data_id", dataId);
            params.put("type", 3);
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

    public void unlockVideo(final int position,int dataId) {
        try {
            mView.hideLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("data_id", dataId);
            params.put("type", "video");
            model.unlock(params, new HttpResultListener<UnlockResultInfo>() {
                @Override
                public void onSuccess(UnlockResultInfo data) {
                    try {
                        mView.showMessage("解锁成功");
                        mView.unlockSuccess(position, data.getScore());
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
