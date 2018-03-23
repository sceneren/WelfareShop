package com.quduo.welfareshop.ui.shop.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
 * Author:scene
 * Time:2018/3/23 10:42
 * Description:创建订单信息
 */

public class CreateOrderInfo extends BaseBean {
    private int choosedNum;
    private String chooseModel;
    private int goodsId;
    private String goodsName;
    private String goodsPrice;
    private String thumb;

    public int getChoosedNum() {
        return choosedNum;
    }

    public void setChoosedNum(int choosedNum) {
        this.choosedNum = choosedNum;
    }

    public String getChooseModel() {
        return chooseModel;
    }

    public void setChooseModel(String chooseModel) {
        this.chooseModel = chooseModel;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
