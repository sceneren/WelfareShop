package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;
import com.quduo.welfareshop.ui.mine.model.MyInfoModel;
import com.quduo.welfareshop.ui.mine.view.IMyInfoView;

/**
 * Author:scene
 * Time:2018/2/2 15:47
 * Description:我的资料
 */
public class MyInfoPresenter extends BasePresenter<IMyInfoView> {
    private MyInfoModel model;

    public MyInfoPresenter(IMyInfoView view) {
        super(view);
        model = new MyInfoModel();
    }

    public void getData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getData(new HttpResultListener<MyUserDetailInfo>() {
                @Override
                public void onSuccess(MyUserDetailInfo data) {
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
