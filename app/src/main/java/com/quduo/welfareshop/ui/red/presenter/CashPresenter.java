package com.quduo.welfareshop.ui.red.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.red.model.CashModel;
import com.quduo.welfareshop.ui.red.view.ICashView;

/**
 * Author:scene
 * Time:2018/3/8 11:28
 * Description:提现
 */

public class CashPresenter extends BasePresenter<ICashView> {
    private CashModel model;

    public CashPresenter(ICashView view) {
        super(view);
        this.model = new CashModel();
    }

    public void cash(int type) {
        try {
            HttpParams params = new HttpParams();
            if (mView.getCost() == 0) {
                mView.showMessage("请输入你要提现的金额");
                return;
            }
            if(mView.getCost()<20){
                mView.showMessage("提现金额必须大于20元");
                return;
            }
            params.put("cost", mView.getCost());
            params.put("type", type);
            if (type == 1) {
                if (StringUtils.isEmpty(mView.getAlipayUsername())) {
                    mView.showMessage("请输入支付宝姓名");
                    return;
                }
                params.put("alipay", mView.getAlipayUsername());
                if (StringUtils.isEmpty(mView.getAlipayAlipayCard())) {
                    mView.showMessage("请输入支付宝账号");
                    return;
                }
                params.put("alipay_name", mView.getAlipayAlipayCard());
            } else {
                if (StringUtils.isEmpty(mView.getBankBankName())) {
                    mView.showMessage("请输入开户行");
                    return;
                }
                params.put("bank", mView.getBankBankName());
                if (StringUtils.isEmpty(mView.getBankBankCard())) {
                    mView.showMessage("请输入银行卡卡号");
                    return;
                }
                params.put("card_id", mView.getBankBankCard());
                if (StringUtils.isEmpty(mView.getBankUsername())) {
                    mView.showMessage("请输入收款人姓名");
                    return;
                }
                params.put("card_name", mView.getBankUsername());
            }
            mView.showLoadingDialog();
            model.cash(params, new HttpResultListener<Boolean>() {
                @Override
                public void onSuccess(Boolean data) {
                    try {
                        mView.cashSuccess();
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
