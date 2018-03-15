package com.quduo.welfareshop.ui.friend.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.entity.NearInfo;
import com.quduo.welfareshop.ui.friend.model.NearModel;
import com.quduo.welfareshop.ui.friend.view.INearView;

import java.util.List;

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

    public void getData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("longitude", mView.getLongitude());
            params.put("latitude", mView.getLatitude());
            params.put("sex", mView.getSex());
            model.getData(params, new HttpResultListener<List<NearInfo>>() {
                @Override
                public void onSuccess(List<NearInfo> data) {
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
}
