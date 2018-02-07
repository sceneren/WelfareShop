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
    private String otherUserId;
    private String otherNickName;
    private String otherAvatar;
    //messageType:0-文本，1-图片，2语音
    private int messageType;
    //messageContent 文本内容或者图片语音文件的路径
    private String messageContent;
    private long time;
    private float audioTime;
    @Generated(hash = 1894518625)
    public ChatMessageInfo(Long id, String otherUserId, String otherNickName,
            String otherAvatar, int messageType, String messageContent, long time,
            float audioTime) {
        this.id = id;
        this.otherUserId = otherUserId;
        this.otherNickName = otherNickName;
        this.otherAvatar = otherAvatar;
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
    public String getOtherUserId() {
        return this.otherUserId;
    }
    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId;
    }
    public String getOtherNickName() {
        return this.otherNickName;
    }
    public void setOtherNickName(String otherNickName) {
        this.otherNickName = otherNickName;
    }
    public String getOtherAvatar() {
        return this.otherAvatar;
    }
    public void setOtherAvatar(String otherAvatar) {
        this.otherAvatar = otherAvatar;
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
    public float getAudioTime() {
        return this.audioTime;
    }
    public void setAudioTime(float audioTime) {
        this.audioTime = audioTime;
    }
   
}
