package com.quduo.welfareshop.activity;

import com.quduo.welfareshop.bean.RechargeInfo;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;

/**
 * Author:scene
 * Time:2018/3/5 17:50
 * Description:充值
 */

public class RechargePresenter extends BasePresenter<IRechargeView> {
    private RechargeModel model;

    public RechargePresenter(IRechargeView view) {
        super(view);
        this.model = new RechargeModel();
    }

    public void getData() {
        try {
            mView.showLoadingPage();
            model.getData(new HttpResultListener<RechargeInfo>() {
                @Override
                public void onSuccess(RechargeInfo data) {
                    try {
                        mView.bindRechargeListView(data);
                        mView.showContentPage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showErrorPage();
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
