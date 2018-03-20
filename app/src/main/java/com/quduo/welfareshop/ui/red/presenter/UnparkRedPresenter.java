package com.quduo.welfareshop.ui.red.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.red.entity.OpenRedResultInfo;
import com.quduo.welfareshop.ui.red.entity.UnparkRedInfo;
import com.quduo.welfareshop.ui.red.model.UnparkRedModel;
import com.quduo.welfareshop.ui.red.view.IUnparkRedView;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/8 9:59
 * Description:未拆开的红包
 */

public class UnparkRedPresenter extends BasePresenter<IUnparkRedView> {
    private UnparkRedModel model;

    public UnparkRedPresenter(IUnparkRedView view) {
        super(view);
        this.model = new UnparkRedModel();
    }

    public void getData(final boolean isFirst) {
        try {
            if (isFirst) {
                mView.showLoadingPage();
            }
            model.getData(new HttpResultListener<List<UnparkRedInfo>>() {
                @Override
                public void onSuccess(List<UnparkRedInfo> data) {
                    try {
                        if (isFirst) {
                            mView.showContentPage();
                        } else {
                            mView.refreshFinish();
                        }
                        mView.bindData(data);
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

    public void openRed(final int position, int redId) {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("bag_id", redId);
            model.openRed(params, new HttpResultListener<OpenRedResultInfo>() {
                @Override
                public void onSuccess(OpenRedResultInfo data) {
                    try {
                        mView.openRedSuccess(position, data);
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
