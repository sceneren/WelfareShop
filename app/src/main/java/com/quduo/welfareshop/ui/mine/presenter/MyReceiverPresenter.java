package com.quduo.welfareshop.ui.mine.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.entity.ReceiverInfo;
import com.quduo.welfareshop.ui.mine.model.MyReceiverModel;
import com.quduo.welfareshop.ui.mine.view.IMyReceiverView;

/**
 * Author:scene
 * Time:2018/3/1 14:08
 * Description:收货信息
 */

public class MyReceiverPresenter extends BasePresenter<IMyReceiverView> {
    private MyReceiverModel model;

    public MyReceiverPresenter(IMyReceiverView view) {
        super(view);
        this.model = new MyReceiverModel();
    }

    public void getData() {
        try {
            mView.showLoadingPage();
            model.getDada(new HttpResultListener<ReceiverInfo>() {
                @Override
                public void onSuccess(ReceiverInfo data) {
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

    public void editReceiver() {
        try {
            if (StringUtils.isEmpty(mView.getName())) {
                mView.showMessage("请输入收货人");
                return;
            }
            if (StringUtils.isEmpty(mView.getPhone())) {
                mView.showMessage("请输入收货电话");
                return;
            }

            if (StringUtils.isEmpty(mView.getAddress())) {
                mView.showMessage("请输入收货地址");
                return;
            }


            mView.showLaodingDialog();
            HttpParams params = new HttpParams();
            params.put("name", mView.getName());
            params.put("address", mView.getAddress());
            params.put("mobile", mView.getPhone());
            model.updateReceiverInfo(params, new HttpResultListener<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    try {
                        mView.showMessage("修改成功");
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
                    mView.hideLoadingDialog();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
