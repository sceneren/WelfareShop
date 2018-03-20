package com.quduo.welfareshop.ui.red.presenter;

import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.red.entity.RedResultInfo;
import com.quduo.welfareshop.ui.red.model.RedModel;
import com.quduo.welfareshop.ui.red.view.IRedView;

/**
 * Author:scene
 * Time:2018/2/1 15:25
 * Description:
 */

public class RedPresenter extends BasePresenter<IRedView> {
    private RedModel model;

    public RedPresenter(IRedView view) {
        super(view);
        this.model = new RedModel();
    }

    public void getData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getData(new HttpResultListener<RedResultInfo>() {
                @Override
                public void onSuccess(RedResultInfo data) {
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
