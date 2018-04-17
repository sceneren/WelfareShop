package com.quduo.welfareshop.ui.shop.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.shop.entity.SingleGoodsDetailResultInfo;
import com.quduo.welfareshop.ui.shop.model.SingleGoodsDetailModel;
import com.quduo.welfareshop.ui.shop.view.ISingleGoodsDetailView;
import com.quduo.welfareshop.ui.welfare.entity.FollowSuccessInfo;

public class SingleGoodsDetailPresenter extends BasePresenter<ISingleGoodsDetailView> {
    private SingleGoodsDetailModel model;

    public SingleGoodsDetailPresenter(ISingleGoodsDetailView view) {
        super(view);
        this.model = new SingleGoodsDetailModel();
    }

    public void getData() {
        try {
            mView.showLoadingPage();
            model.getData(new HttpResultListener<SingleGoodsDetailResultInfo>() {
                @Override
                public void onSuccess(SingleGoodsDetailResultInfo data) {
                    try {
                        mView.showContentPage();
                        mView.bindData(data);
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

    public void followGoods() {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("data_id", mView.getGoodsId());
            model.followGoods(params, new HttpResultListener<FollowSuccessInfo>() {
                @Override
                public void onSuccess(FollowSuccessInfo data) {
                    try {
                        mView.followGoodsSuccess(data.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        mView.hideLoadingDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelFollowGoods(int followId) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("id", followId);
            model.cancelFollow(params, new HttpResultListener<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    try {
                        mView.cancelFollowSuccess();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        mView.hideLoadingDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
