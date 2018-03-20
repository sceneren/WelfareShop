package com.quduo.welfareshop.ui.red.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.red.entity.OpenRedResultInfo;
import com.quduo.welfareshop.ui.red.entity.UnparkRedInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/8 9:59
 * Description:未拆开的红包
 */

public interface IUnparkRedView extends BaseView {
    void showMessage(String message);

    void showLoadingDialog();

    void hideLoadingDialog();

    void refreshFinish();

    void bindData(List<UnparkRedInfo> data);

    void openRedSuccess(int position,OpenRedResultInfo data);
}
