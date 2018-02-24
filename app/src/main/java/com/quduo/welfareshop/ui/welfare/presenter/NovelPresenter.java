package com.quduo.welfareshop.ui.welfare.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.model.NovelModel;
import com.quduo.welfareshop.ui.welfare.view.INovelView;

/**
 * Author:scene
 * Time:2018/2/24 11:14
 * Description:小爽文
 */

public class NovelPresenter extends BasePresenter<INovelView> {
    private NovelModel model;

    public NovelPresenter(INovelView view) {
        this.mView = view;
        this.model = new NovelModel();
    }
}
