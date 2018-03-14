package com.quduo.welfareshop.ui.red.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.red.model.HistoryRedModel;
import com.quduo.welfareshop.ui.red.view.IHistoryRedView;

/**
 * Author:scene
 * Time:2018/3/8 10:00
 * Description:历史记录
 */

public class HistoryRedPresenter extends BasePresenter<IHistoryRedView> {
    private HistoryRedModel model;

    public HistoryRedPresenter(IHistoryRedView view) {
        super(view);
        this.model = new HistoryRedModel();
    }
}
