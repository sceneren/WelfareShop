package com.quduo.welfareshop.ui.shop.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.shop.entity.GoodsDetailResultInfo;
import com.quduo.welfareshop.ui.shop.model.GoodsDetailModel;
import com.quduo.welfareshop.ui.shop.view.IGoodsDetailView;

/**
 * Author:scene
 * Time:2018/2/27 15:14
 * Description:商品详情
 */

public class GoodsDetailPresenter extends BasePresenter<IGoodsDetailView> {
    private GoodsDetailModel model;

    public GoodsDetailPresenter(IGoodsDetailView view) {
        super(view);
        this.model = new GoodsDetailModel();
    }

    public void getData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("id", mView.getGoodsId());
            model.getData(params, new HttpResultListener<GoodsDetailResultInfo>() {
                @Override
                public void onSuccess(GoodsDetailResultInfo data) {
                    try {
                        mView.bindData(data);
                        if(isFirst){
                            mView.showContentPage();
                        }else{
                            mView.refreshFinish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        if(isFirst){
                            mView.showErrorPage();
                        }else{
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
