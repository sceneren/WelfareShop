package com.quduo.welfareshop.ui.shop.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.shop.entity.GoodsCommentResultInfo;
import com.quduo.welfareshop.ui.shop.model.GoodsCommentModel;
import com.quduo.welfareshop.ui.shop.view.IGoodsCommentView;

/**
 * Author:scene
 * Time:2018/2/28 15:42
 * Description:买家秀
 */

public class GoodsCommentPresenter extends BasePresenter<IGoodsCommentView> {
    private GoodsCommentModel model;

    public GoodsCommentPresenter(IGoodsCommentView view) {
        super(view);
        this.model = new GoodsCommentModel();
    }


    public void getData(final int page, final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("page", page);
            params.put("product_id", mView.getGoodsId());
            model.getData(params, new HttpResultListener<GoodsCommentResultInfo>() {
                @Override
                public void onSuccess(GoodsCommentResultInfo data) {
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
