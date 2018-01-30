package com.quduo.welfareshop.ui.read.listener;

/**
 * Author:scene
 * Time:2018/1/30 18:02
 * Description:保存数据到数据库的监听器
 */

public interface OnSaveData2DBListener {
    void onSaveSuccess();

    void onSaveFail();

    void onSaveRepeat();
}
