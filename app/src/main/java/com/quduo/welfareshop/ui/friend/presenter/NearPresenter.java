package com.quduo.welfareshop.ui.friend.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.entity.NearResultInfo;
import com.quduo.welfareshop.ui.friend.model.NearModel;
import com.quduo.welfareshop.ui.friend.view.INearView;

/**
 * Author:scene
 * Time:2018/2/1 17:09
 * Description:附近的人
 */

public class NearPresenter extends BasePresenter<INearView> {
    private NearModel model;

    public NearPresenter(INearView view) {
        super(view);
        this.model = new NearModel();
    }

    public void getData(final boolean isFirst, final int page, int maxDistance) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("longitude", mView.getLongitude());
            params.put("latitude", mView.getLatitude());
            params.put("sex", mView.getSex());
            params.put("page", page);
            params.put("max_distance", maxDistance);
            model.getData(params, new HttpResultListener<NearResultInfo>() {
                @Override
                public void onSuccess(NearResultInfo data) {
                    try {
                        mView.bindData(data);
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            if (data.getCurrent_page() == 1) {
                                mView.refreshFinish();
                            } else {
                                mView.loadmoreFinish();
                            }
                        }
                        mView.setHasMore(data.getCurrent_page() < data.getLast_page());
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
                            if (page == 1) {
                                mView.refreshFinish();
                            } else {
                                mView.loadmoreFinish();
                                mView.setHasMore(true);
                            }
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
}
