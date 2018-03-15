package com.quduo.welfareshop.ui.mine.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
 * Author:scene
 * Time:2018/3/15 15:03
 * Description:上传图片返回
 */
public class UploadAvatarResultInfo extends BaseBean {

    /**
     * avatar : /avatar/2.png
     */

    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
