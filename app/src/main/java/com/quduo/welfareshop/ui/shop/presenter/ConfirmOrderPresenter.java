package com.quduo.welfareshop.ui.shop.presenter;

import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.shop.entity.ConfirmOrderResultInfo;
import com.quduo.welfareshop.ui.shop.model.ConfirmOrderModel;
import com.quduo.welfareshop.ui.shop.view.IConfirmOrderView;

/**
 * Author:scene
 * Time:2018/3/13 15:35
 * Description:确认订单
 */

public class ConfirmOrderPresenter extends BasePresenter<IConfirmOrderView> {
    private ConfirmOrderModel model;

    public ConfirmOrderPresenter(IConfirmOrderView view) {
        super(view);
        this.model = new ConfirmOrderModel();
    }

    public void getData() {
        try {
            mView.showLoadingPage();
            model.getData(new HttpResultListener<ConfirmOrderResultInfo>() {
                @Override
                public void onSuccess(ConfirmOrderResultInfo data) {
                    try {
                        mView.bindData(data);
                        mView.showContentPage();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
                        mView.showErrorPage();
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
