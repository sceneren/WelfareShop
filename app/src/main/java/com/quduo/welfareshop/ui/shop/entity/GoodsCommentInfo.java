package com.quduo.welfareshop.ui.shop.entity;

import com.quduo.welfareshop.base.BaseBean;

import java.util.List;

/**
*Author:scene
*Time:2018/3/22 15:49
*Description:商品评论
*/

public class GoodsCommentInfo extends BaseBean{

    /**
     * id : 1
     * product_id : 1
     * nick_name : 测试
     * create_time : 2018-03-22 15:50:06
     * content : 测试测试
     * images : ["/goods/1/1.jpg"]
     */

    private int id;
    private int product_id;
    private String nick_name;
    private long create_time;
    private String content;
    private List<String> images;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
