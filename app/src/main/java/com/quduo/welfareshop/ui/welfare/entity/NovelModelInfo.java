package com.quduo.welfareshop.ui.welfare.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Author:scene
 * Time:2018/2/24 11:58
 * Description:小说模块 大图  竖图  广告
 */

public class NovelModelInfo implements Serializable {
    private int type;
    private List<NovelIndexInfo> list;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<NovelIndexInfo> getList() {
        return list;
    }

    public void setList(List<NovelIndexInfo> list) {
        this.list = list;
    }
}
