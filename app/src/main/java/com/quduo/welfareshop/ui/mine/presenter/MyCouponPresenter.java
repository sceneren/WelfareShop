package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.entity.CouponInfo;
import com.quduo.welfareshop.ui.mine.model.MyCouponModel;
import com.quduo.welfareshop.ui.mine.view.IMyCouponView;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 13:47
 * Description:我的代金券
 */

public class MyCouponPresenter extends BasePresenter<IMyCouponView> {
    private MyCouponModel model;

    public MyCouponPresenter(IMyCouponView view) {
        super(view);
        this.model = new MyCouponModel();
    }

    public void getData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getData(new HttpResultListener<List<CouponInfo>>() {
                @Override
                public void onSuccess(List<CouponInfo> data) {
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
