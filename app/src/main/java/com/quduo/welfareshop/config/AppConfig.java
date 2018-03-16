package com.quduo.welfareshop.config;

import android.os.Environment;

import java.io.File;

/**
 * Author:scene
 * Time:2018/2/23 14:20
 * Description:配置文件
 */

public class AppConfig {
    public static final String userId = "10001";
    public static final String NOVEL_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "welfareshop" + File.separator;
    public static final int VIDEO_TYPE_SMALL_VIDEO = 1;
    public static final int VIDEO_TYPE_BEAUTY_VIDEO = 2;
    public static final int VIDEO_TYPE_MIDNIGHT_VIDEO = 3;
}
