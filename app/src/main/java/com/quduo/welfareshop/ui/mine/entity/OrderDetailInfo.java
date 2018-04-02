package com.quduo.welfareshop.ui.mine.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
*Author:scene
*Time:2018/3/23 15:25
*Description:订单详情
*/

public class OrderDetailInfo extends BaseBean {

    /**
     * id : 7
     * user_id : 3
     * product_id : 6
     * order_id : 223823018444mWFa
     * model : 白色
     * number : 1
     * price : 128
     * cost : 128.00
     * actual_pay : 128.00
     * create_time : 1522382301
     * status : 3
     * name : 岳峰
     * address : 实话实说计算机
     * mobile : 13507274561
     * pay_type : 1
     * score : 128
     * diamond : 128
     * coupon_id : 0
     * coupon_money : 0.00
     * express_company : 1
     * express_number : sf1234567890
     * product_cost : 68.00
     * product_name : 趴屁
     * thumb : /img/2018/03/0d4a7bafc17944dd19f6e4e16f5c6f07.jpeg
     */

    private int id;
    private int user_id;
    private int product_id;
    private String order_id;
    private String model;
    private int number;
    private String price;
    private String cost;
    private String actual_pay;
    private int create_time;
    private int status;
    private String name;
    private String address;
    private String mobile;
    private int pay_type;
    private int score;
    private int diamond;
    private int coupon_id;
    private String coupon_money;
    private String express_company;
    private String express_number;
    private String product_cost;
    private String product_name;
    private String thumb;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getActual_pay() {
        return actual_pay;
    }

    public void setActual_pay(String actual_pay) {
        this.actual_pay = actual_pay;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getCoupon_money() {
        return coupon_money;
    }

    public void setCoupon_money(String coupon_money) {
        this.coupon_money = coupon_money;
    }

    public String getExpress_company() {
        return express_company;
    }

    public void setExpress_company(String express_company) {
        this.express_company = express_company;
    }

    public String getExpress_number() {
        return express_number;
    }

    public void setExpress_number(String express_number) {
        this.express_number = express_number;
    }

    public String getProduct_cost() {
        return product_cost;
    }

    public void setProduct_cost(String product_cost) {
        this.product_cost = product_cost;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
