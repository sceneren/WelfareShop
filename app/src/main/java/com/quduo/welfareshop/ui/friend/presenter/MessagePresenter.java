package com.quduo.welfareshop.ui.friend.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.model.MessageModel;
import com.quduo.welfareshop.ui.friend.view.IMessageView;

/**
 * Author:scene
 * Time:2018/2/1 17:09
 * Description:消息
 */

public class MessagePresenter extends BasePresenter<IMessageView> {
    private MessageModel model;

    public MessagePresenter(IMessageView view) {
        this.mView = view;
        model = new MessageModel();
    }
}
