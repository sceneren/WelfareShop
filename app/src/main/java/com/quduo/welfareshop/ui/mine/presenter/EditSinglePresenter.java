package com.quduo.welfareshop.ui.mine.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.mine.model.EditSingleModel;
import com.quduo.welfareshop.ui.mine.view.IEditSingleView;

/**
 * Author:scene
 * Time:2018/2/5 9:40
 * Description:文本单项修改
 */

public class EditSinglePresenter extends BasePresenter<IEditSingleView> {
    private EditSingleModel model;

    public EditSinglePresenter(IEditSingleView view) {
        this.mView = view;
        this.model = new EditSingleModel();
    }
}
