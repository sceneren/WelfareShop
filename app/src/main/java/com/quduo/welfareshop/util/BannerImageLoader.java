package com.quduo.welfareshop.util;

import android.content.Context;
import android.widget.ImageView;

import com.quduo.welfareshop.base.GlideApp;
import com.youth.banner.loader.ImageLoader;

/**
 * banner图片加载
 * Created by scene on 2018/1/5.
 */

public class BannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        GlideApp.with(context).load(path).into(imageView);
    }
}