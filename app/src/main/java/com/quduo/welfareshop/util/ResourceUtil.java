package com.quduo.welfareshop.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

/**
 * Author:scene
 * Time:2018/1/26 13:17
 * Description:资源获取
 */
public class ResourceUtil {
    /**
     * Case By:获取渠道Id
     * Author: scene on 2017/5/19 10:46
     */
    public static String getResouyceId(Context context) {
        String resultData = "";
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        int str = applicationInfo.metaData.getInt("CHANNEL", 1001);
                        resultData = String.valueOf(str);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }
}
