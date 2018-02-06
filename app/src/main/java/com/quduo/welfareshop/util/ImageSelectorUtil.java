package com.quduo.welfareshop.util;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.quduo.welfareshop.R;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.config.ISListConfig;

/**
 * Author:scene
 * Time:2018/2/6 14:36
 * Description:图片选择
 */

public class ImageSelectorUtil {
    public static void openImageList(Activity activity, int needPhotoNum, int requestCode) {
        // 自由配置选项
        ISListConfig config = new ISListConfig.Builder()
                // 是否多选, 默认true
                .multiSelect(true)
                // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                .rememberSelected(false)
                // “确定”按钮背景色
                .btnBgColor(Color.TRANSPARENT)
                // “确定”按钮文字颜色
                .btnTextColor(Color.WHITE)
                // 使用沉浸式状态栏
                .statusBarColor(ContextCompat.getColor(activity, R.color.colorAccent))
                // 返回图标ResId
                .backResId(R.drawable.ic_toolbar_back)
                // 标题
                .title("图片")
                // 标题文字颜色
                .titleColor(Color.WHITE)
                // TitleBar背景色
                .titleBgColor(ContextCompat.getColor(activity, R.color.colorAccent))
//                // 裁剪大小。needCrop为true的时候配置
//                .cropSize(1, 1, 200, 200)
//                .needCrop(true)
                // 第一个是否显示相机，默认true
                .needCamera(true)
                // 最大选择图片数量，默认9
                .maxNum(needPhotoNum)
                .build();
        // 跳转到图片选择器
        ISNav.getInstance().toListActivity(activity, config, requestCode);
    }
}
