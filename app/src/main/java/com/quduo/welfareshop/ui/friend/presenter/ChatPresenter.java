package com.quduo.welfareshop.ui.friend.presenter;

import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.entity.ChatMessageInfo;
import com.quduo.welfareshop.ui.friend.model.ChatModel;
import com.quduo.welfareshop.ui.friend.view.IChatView;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/5 16:40
 * Description:聊天
 */

public class ChatPresenter extends BasePresenter<IChatView> {
    private ChatModel model;

    public ChatPresenter(IChatView view) {
        this.mView = view;
        this.model = new ChatModel();
    }

    public void getAllMessage(boolean isRefresh,boolean isFirstEnter) {
        try {
            List<ChatMessageInfo> list = model.getAllMessage(this.mView.getOtherUserId());
            mView.refreshComplete();
            mView.updateRecyclerView(list);
            if(!isRefresh){
                mView.moveToBottom(isFirstEnter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(ChatMessageInfo chatMessageInfo) {
        try {
            model.sendTextMessage(chatMessageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
