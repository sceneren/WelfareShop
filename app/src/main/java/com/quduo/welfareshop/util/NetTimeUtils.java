package com.quduo.welfareshop.util;

/**
 * 获取网络时间
 * Created by scene on 2017/11/24.
 */

public class NetTimeUtils {
    private static long errorTime = 0;

    public static long getWebsiteDatetime() {
        try {
            return System.currentTimeMillis() + errorTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    public static void setErrorTime(long serviceTime) {
        errorTime = serviceTime * 1000 - 1000 - System.currentTimeMillis();
    }
}
