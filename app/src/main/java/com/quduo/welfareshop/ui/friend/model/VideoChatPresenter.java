package com.quduo.welfareshop.ui.friend.model;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.view.IVideoChatView;

/**
 * Author:scene
 * Time:2018/3/5 10:55
 * Description:视频聊天
 */

public class VideoChatPresenter extends BasePresenter<IVideoChatView> {
    public VideoChatPresenter(IVideoChatView view) {
        this.mView = view;
    }
}
