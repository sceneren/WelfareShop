package com.quduo.welfareshop.ui.friend.model;

import com.quduo.welfareshop.greendao.dao.MessageInfoDao;
import com.quduo.welfareshop.ui.friend.entity.ChatMessageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:scene
 * Time:2018/2/1 17:05
 * Description:消息
 */

public class MessageModel {
    public List<ChatMessageInfo> getAllSeesion() {
        try {
            return MessageInfoDao.getInstance().querySession();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void deleteSession(String otherId) {
        try {
            MessageInfoDao.getInstance().deleteSession(otherId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
