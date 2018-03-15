package com.quduo.welfareshop.event;

/**
 * Author:scene
 * Time:2018/3/15 16:05
 * Description:修改头像
 */

public class UpdateAvatarEvent {
    private String avatarPath;

    public String getAvatarPath() {
        return avatarPath;
    }

    public UpdateAvatarEvent(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
