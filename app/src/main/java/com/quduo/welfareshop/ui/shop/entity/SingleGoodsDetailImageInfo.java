package com.quduo.welfareshop.ui.shop.entity;

import java.io.Serializable;

public class SingleGoodsDetailImageInfo implements Serializable {
    private String img;
    private int width;
    private int height;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
