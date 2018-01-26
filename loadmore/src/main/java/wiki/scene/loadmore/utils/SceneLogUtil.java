package wiki.scene.loadmore.utils;

import android.util.Log;

/**
 * FileName:com.scene.baselib.util.LogUtil.java
 * 功能描述：日志工具类
 * author: scene
 * date: 2016-07-19 11:04
 */
public class SceneLogUtil {
    private static boolean isShowLog = true;
    private static final String DEFULT_TAG = "com.scene";

    public static void v(String str) {
        if (isShowLog) {
            Log.i(DEFULT_TAG, str);
        }
    }

    public static void v(String tag, String str) {
        if (isShowLog) {
            Log.i(tag, str);
        }
    }


    public static void i(String str) {
        if (isShowLog) {
            Log.i(DEFULT_TAG, str);
        }
    }

    public static void i(String tag, String str) {
        if (isShowLog) {
            Log.i(tag, str);
        }
    }

    public static void d(String str) {
        if (isShowLog) {
            Log.d(DEFULT_TAG, str);
        }
    }

    public static void d(String tag, String str) {
        if (isShowLog) {
            Log.d(tag, str);
        }
    }

    public static void w(String str) {
        if (isShowLog) {
            Log.w(DEFULT_TAG, str);
        }
    }

    public static void w(String tag, String str) {
        if (isShowLog) {
            Log.w(tag, str);
        }
    }

    public static void e(String str) {
        if (isShowLog) {
            Log.e(DEFULT_TAG, str);
        }
    }

    public static void e(String tag, String str) {
        if (isShowLog) {
            Log.e(tag, str);
        }
    }


}
