package com.quduo.welfareshop.ui.welfare.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.welfare.model.NovelCommentModel;
import com.quduo.welfareshop.ui.welfare.view.INovelCommentView;

public class NovelCommentPresenter extends BasePresenter<INovelCommentView> {
    private NovelCommentModel model;

    public NovelCommentPresenter(INovelCommentView view) {
        super(view);
        this.model = new NovelCommentModel();
    }


}
