package com.quduo.welfareshop.util;

import java.math.BigDecimal;

/**
 * Author:scene
 * Time:2018/3/15 12:23
 * Description:优化距离显示
 */

public class DistanceUtil {
    public static String formatDistance(int distance) {
        if (distance < 1000) {
            return distance + "m";
        } else {
            float dis = distance / 1000f;
            BigDecimal b = new BigDecimal(dis);
            return b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() + "km";
        }
    }
}
