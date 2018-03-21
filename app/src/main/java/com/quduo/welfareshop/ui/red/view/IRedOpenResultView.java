package com.quduo.welfareshop.ui.red.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.red.entity.RedOpenResultInfo;

/**
 * Author:scene
 * Time:2018/3/21 12:27
 * Description:红包开奖结果
 */

public interface IRedOpenResultView extends BaseView {
    void showMessage(String message);

    void bindData(RedOpenResultInfo data);
}
