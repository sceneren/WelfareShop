package com.quduo.welfareshop.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;

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

    //获取手机的IMEI
    public static String getImei() {
        //先获取SP里面是否存了IMEI
        String imei = SPUtils.getInstance().getString("IMEI", "");
        //没存就通过系统获取IMEI
        if (StringUtils.isTrimEmpty(imei)) {
            imei = PhoneUtils.getIMEI();
            //系统获取IMEI失败，就自动生成一个IMEI
            if (StringUtils.isTrimEmpty(imei)) {
                imei = createRandomUUID(false, 32);
                SPUtils.getInstance().put("IMEI", imei);
            }
        }
        return imei;
    }

    private static String createRandomUUID(boolean numberFlag, int length) {
        String retStr;
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 20) {
                bDone = false;
            }
        } while (bDone);
        retStr = retStr + System.currentTimeMillis();
        return retStr;
    }
}
