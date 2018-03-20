package com.quduo.welfareshop.ui.red.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.red.entity.RedResultInfo;

/**
 * Author:scene
 * Time:2018/2/1 15:24
 * Description:红包
 */

public interface IRedView extends BaseView {

    void bindData(RedResultInfo data);

    void refreshFinish();

    void showMessage(String message);

    void buyRedSuccess(int diamondNumber);

    void showLoadingDialog();

    void hideLoadingDialog();

    void hideGetRedDialog();

    void showNeedGetDiamondDialog();

    void hideNeedGetDiamondDialog();
}
