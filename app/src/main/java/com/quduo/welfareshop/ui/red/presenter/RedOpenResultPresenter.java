package com.quduo.welfareshop.ui.red.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.red.entity.RedOpenResultInfo;
import com.quduo.welfareshop.ui.red.model.RedOpenResultModel;
import com.quduo.welfareshop.ui.red.view.IRedOpenResultView;

/**
 * Author:scene
 * Time:2018/3/21 12:28
 * Description:红包开奖结果
 */

public class RedOpenResultPresenter extends BasePresenter<IRedOpenResultView> {
    private RedOpenResultModel model;

    public RedOpenResultPresenter(IRedOpenResultView view) {
        super(view);
        this.model = new RedOpenResultModel();
    }

    public void getData(int redId) {
        try {
            mView.showLoadingPage();
            HttpParams params = new HttpParams();
            params.put("bag_id", redId);
            model.getData(params, new HttpResultListener<RedOpenResultInfo>() {
                @Override
                public void onSuccess(RedOpenResultInfo data) {
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
