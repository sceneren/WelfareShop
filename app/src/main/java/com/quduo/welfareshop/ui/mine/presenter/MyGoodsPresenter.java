package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.entity.MyFollowGoodsInfo;
import com.quduo.welfareshop.ui.mine.model.MyGoodsModel;
import com.quduo.welfareshop.ui.mine.view.IMyGoodsView;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 10:38
 * Description:我收藏的商品
 */

public class MyGoodsPresenter extends BasePresenter<IMyGoodsView> {
    private MyGoodsModel model;

    public MyGoodsPresenter(IMyGoodsView view) {
        super(view);
        this.model = new MyGoodsModel();
    }

    public void getData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getData(new HttpResultListener<List<MyFollowGoodsInfo>>() {
                @Override
                public void onSuccess(List<MyFollowGoodsInfo> data) {
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
                        if (isFirst) {
                            mView.showErrorPage();
                        } else {
                            mView.refreshFinish();
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
