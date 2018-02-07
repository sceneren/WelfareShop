package com.quduo.welfareshop.util;

import com.blankj.utilcode.constant.TimeConstants;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Instant;

import java.util.Locale;

/**
 * Author:scene
 * Time:2018/2/7 14:11
 * Description:时间输出类
 */

public class Time2StringUtils {

    public static String millis2String(long millis, String pattern) {
        DateTime dateTime = new DateTime(millis);
        return dateTime.toString(pattern);
    }

    /**
     * Author:scene
     * Time:2018/2/7 14:13
     * Description:距离现在过去好久
     */
    public static String millisDistanceCurrent(long millis) {
        Instant instant = new Instant();
        Duration d = new Duration(millis, instant.getMillis());
        long distanceMillis = d.getMillis();

        if (distanceMillis < 1000) {
            return "刚刚";
        } else if (distanceMillis < TimeConstants.MIN) {
            return String.format(Locale.getDefault(), "%d秒前", distanceMillis / TimeConstants.SEC);
        } else if (distanceMillis < TimeConstants.HOUR) {
            return String.format(Locale.getDefault(), "%d分钟前", distanceMillis / TimeConstants.MIN);
        }
        // 获取当天 00:00
        long wee = getWeeOfToday();

        if (millis >= wee) {
            return String.format(Locale.getDefault(), "%d小时前", distanceMillis / TimeConstants.HOUR);
        } else {
            DateTime dateTime = new DateTime(millis);
            return dateTime.toString("HH-mm");
        }

    }

    /**
     * Author:scene
     * Time:2018/2/7 14:25
     * Description:获取当天0点
     */
    private static long getWeeOfToday() {
        DateTime dateTime = new DateTime();
        DateTime instant1 = new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfMonth(), 0, 0, 0);
        return instant1.getMillis();
    }
}
