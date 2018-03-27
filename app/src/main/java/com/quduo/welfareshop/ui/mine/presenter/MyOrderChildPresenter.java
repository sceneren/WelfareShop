package com.quduo.welfareshop.ui.mine.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.entity.OrderListResultInfo;
import com.quduo.welfareshop.ui.mine.model.MyOrderChildModel;
import com.quduo.welfareshop.ui.mine.view.IMyOrderChildView;

/**
 * Author:scene
 * Time:2018/2/28 18:07
 * Description:订单子页面
 */

public class MyOrderChildPresenter extends BasePresenter<IMyOrderChildView> {
    private MyOrderChildModel model;

    public MyOrderChildPresenter(IMyOrderChildView view) {
        super(view);
        this.model = new MyOrderChildModel();
    }

    public void getData(final int page, final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            HttpParams params = new HttpParams();
            params.put("page", page);
            params.put("status", mView.getOrderType());
            model.getData(params, new HttpResultListener<OrderListResultInfo>() {
                @Override
                public void onSuccess(OrderListResultInfo data) {
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
                        mView.setHasmore(data.getInfo().getPage_total() > page);
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
                            if (page == 1) {
                                mView.refreshFinish();
                            } else {
                                mView.loadmoreFinish();
                                mView.setHasmore(true);
                            }
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

    public void cancelOrder(final int position, int orderId) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("order_id", orderId);
            model.cancelOrder(params, new HttpResultListener<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    try {
                        mView.cancelOrderSuccess(position);
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
