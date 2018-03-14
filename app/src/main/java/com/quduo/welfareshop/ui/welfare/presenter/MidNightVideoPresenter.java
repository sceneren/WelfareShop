package com.quduo.welfareshop.ui.welfare.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.entity.MidNightVideoResultInfo;
import com.quduo.welfareshop.ui.welfare.model.MidNightVideoModel;
import com.quduo.welfareshop.ui.welfare.view.IMidNightVideoView;

/**
 * Author:scene
 * Time:2018/2/24 10:09
 * Description:午夜影院
 */
public class MidNightVideoPresenter extends BasePresenter<IMidNightVideoView> {
    private MidNightVideoModel model;

    public MidNightVideoPresenter(IMidNightVideoView view) {
        super(view);
        this.model = new MidNightVideoModel();
    }

    public void getMidNightVideoData(int page, final boolean isFirst) {
        try {
            HttpParams params = new HttpParams();
            params.put("page", page);
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getMidNightVideoData(params, new HttpResultListener<MidNightVideoResultInfo>() {

                @Override
                public void onSuccess(MidNightVideoResultInfo data) {
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
