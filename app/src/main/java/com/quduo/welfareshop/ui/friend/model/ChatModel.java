package com.quduo.welfareshop.ui.friend.model;

import com.quduo.welfareshop.greendao.dao.MessageInfoDao;
import com.quduo.welfareshop.ui.friend.entity.ChatMessageInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/5 16:40
 * Description:聊天
 */
public class ChatModel {
    public List<ChatMessageInfo> getAllMessage(String othersUserId) {
        return MessageInfoDao.getInstance().queryUserByUserId(othersUserId);
    }

    public void sendTextMessage(ChatMessageInfo chatMessageInfo) {
        MessageInfoDao.getInstance().insertUserData(chatMessageInfo);
    }

}
