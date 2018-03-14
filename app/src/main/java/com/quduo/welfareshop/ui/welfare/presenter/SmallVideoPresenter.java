package com.quduo.welfareshop.ui.welfare.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.entity.SmallVideoResultInfo;
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
}
