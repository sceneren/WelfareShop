package com.quduo.welfareshop.ui.welfare.entity;

import com.quduo.welfareshop.base.BaseBean;

/**
 * Created by scene on 2018/3/9.
 */

public class GalleryCateInfo extends BaseBean {

    /**
     * name : 制服诱惑
     * thumb : /img/2018/03/15a9adcafb290cf0e42e448169c8ef83.jpeg
     * id : 2
     */

    private String name;
    private String thumb;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
