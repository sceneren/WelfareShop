package com.quduo.welfareshop.ui.shop.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.shop.entity.ShopDataInfo;
import com.quduo.welfareshop.ui.shop.model.CateModel;
import com.quduo.welfareshop.ui.shop.view.ICateView;

/**
 * Author:scene
 * Time:2018/3/22 14:28
 * Description:商品分类
 */

public class CatePresenter extends BasePresenter<ICateView> {
    private CateModel model;

    public CatePresenter(ICateView view) {
        super(view);
        this.model = new CateModel();
    }

    public void getData(final int page, final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("page", page);
            params.put("cate_id", mView.getCateId());
            model.getData(params, new HttpResultListener<ShopDataInfo>() {
                @Override
                public void onSuccess(ShopDataInfo data) {
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
                        mView.hasLoadmore(page < data.getLast_page());
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
