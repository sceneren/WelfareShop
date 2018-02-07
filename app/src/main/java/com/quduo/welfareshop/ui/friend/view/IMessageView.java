package com.quduo.welfareshop.ui.friend.view;


import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.friend.entity.ChatMessageInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/1 17:07
 * Description:消息
 */
public interface IMessageView extends BaseView {
    void getAllSessionInfoSuccess(List<ChatMessageInfo> list);
}
