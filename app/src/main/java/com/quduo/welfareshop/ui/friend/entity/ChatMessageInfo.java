package com.quduo.welfareshop.ui.friend.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;


/**
 * Author:scene
 * Time:2018/2/6 11:36
 * Description:聊天的消息
 */
@Entity
public class ChatMessageInfo {
    @Id(autoincrement = true)
    private Long id;
    private long otherUserId;
    private String otherNickName;
    //messageType:0-文本，1-图片，2语音
    private int messageType;
    //messageContent 文本内容或者图片语音文件的路径
    private String messageContent;
    private long time;
    private int audioTime;
    @Generated(hash = 2035679109)
    public ChatMessageInfo(Long id, long otherUserId, String otherNickName,
            int messageType, String messageContent, long time, int audioTime) {
        this.id = id;
        this.otherUserId = otherUserId;
        this.otherNickName = otherNickName;
        this.messageType = messageType;
        this.messageContent = messageContent;
        this.time = time;
        this.audioTime = audioTime;
    }
    @Generated(hash = 1650910347)
    public ChatMessageInfo() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getOtherUserId() {
        return this.otherUserId;
    }
    public void setOtherUserId(long otherUserId) {
        this.otherUserId = otherUserId;
    }
    public String getOtherNickName() {
        return this.otherNickName;
    }
    public void setOtherNickName(String otherNickName) {
        this.otherNickName = otherNickName;
    }
    public int getMessageType() {
        return this.messageType;
    }
    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }
    public String getMessageContent() {
        return this.messageContent;
    }
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
    public long getTime() {
        return this.time;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public int getAudioTime() {
        return this.audioTime;
    }
    public void setAudioTime(int audioTime) {
        this.audioTime = audioTime;
    }
   
    
}
