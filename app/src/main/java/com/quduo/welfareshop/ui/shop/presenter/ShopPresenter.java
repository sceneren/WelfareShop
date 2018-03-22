package com.quduo.welfareshop.ui.shop.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.shop.entity.ShopResultInfo;
import com.quduo.welfareshop.ui.shop.model.ShopModel;
import com.quduo.welfareshop.ui.shop.view.IShopView;

/**
 * Author:scene
 * Time:2018/1/25  11:17
 * Description:商城主界面
 */
public class ShopPresenter extends BasePresenter<IShopView> {
    private ShopModel model;

    public ShopPresenter(IShopView view) {
        super(view);
        this.model = new ShopModel();
    }

    public void getData(final int page, final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("page", page);
            model.getData(params, new HttpResultListener<ShopResultInfo>() {
                @Override
                public void onSuccess(ShopResultInfo data) {
                    try {
                        mView.bindData(data);
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            if (page == 1) {
                                mView.refreshFinish();
                            } else {
                                mView.loadmoreFinish();
                            }
                        }
                        mView.hasLoadmore(page < data.getData().getLast_page());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            if (page == 1) {
                                mView.refreshFinish();
                            } else {
                                mView.loadmoreFinish();
                                mView.hasLoadmore(true);
                            }
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
