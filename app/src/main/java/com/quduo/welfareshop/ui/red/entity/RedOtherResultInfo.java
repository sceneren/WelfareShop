package com.quduo.welfareshop.ui.red.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
 * Author:scene
 * Time:2018/3/21 12:43
 * Description:开红包我的结果
 */

public class RedOtherResultInfo extends BaseBean {


    /**
     * user_id : 886
     * bonus : 1.30
     * nickname : 莫寻
     * avatar : /avatar/3/m/886/A.png
     * create_time : 1521532443
     * is_best : false
     */

    private int user_id;
    private String bonus;
    private String nickname;
    private String avatar;
    private long create_time;
    private boolean is_best;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public boolean isIs_best() {
        return is_best;
    }

    public void setIs_best(boolean is_best) {
        this.is_best = is_best;
    }
}
