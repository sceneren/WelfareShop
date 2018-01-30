package com.quduo.welfareshop.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 目录工具栏
 * Created by scene on 2018/1/30.
 */

public class CatalogUtil {
    //去掉目录中的制表符
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
