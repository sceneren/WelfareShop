package com.quduo.welfareshop.ui.welfare.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.model.NovelDetailModel;
import com.quduo.welfareshop.ui.welfare.view.INovelDetailView;

/**
 * Author:scene
 * Time:2018/2/26 16:29
 * Description:小说详情
 */

public class NovelDetailPresenter extends BasePresenter<INovelDetailView> {
    private NovelDetailModel model;

    public NovelDetailPresenter(INovelDetailView view) {
        this.mView = view;
        this.model = new NovelDetailModel();
    }
}
