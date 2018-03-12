package com.quduo.welfareshop.http.api;

import com.blankj.utilcode.util.AppUtils;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.util.MD5Util;
import com.quduo.welfareshop.util.NetTimeUtils;

import java.util.HashMap;


/**
 * Case By:API
 * package:wiki.scene.shop.http.api
 * Author：scene on 2017/6/27 12:51
 */

public class ApiUtil {
    private static final String SIGN_KEY = "045448f765b0c0592563123a2652fb63";
    public static final String API_PRE = "http://192.168.0.88:9091/";

    public static final String LOGIN = "user/login";
    public static final String LOGIN_TAG = "user/login";
    //图库
    public static final String GALLERY = "gallery";
    public static final String GALLERY_TAG = "gallery";
    //图库分类列表
    public static final String GALLERY_CATE = "gallery/cate";
    public static final String GALLERY_CATE_TAG = "gallery/cate";
    //图库详情
    public static final String GALLERY_DETAIL = "/gallery/detail";
    public static final String GALLERY_DETAIL_TAG = "/gallery/detail";
    //小视频
    public static final String SMALL_VIDEO_LIST = "micro_video";
    public static final String SMALL_VIDEO_LIST_TAG = "micro_video";
    //美女视频
    public static final String BEAUTY_VIDEO = "video";
    public static final String BEAUTY_VIDEO_TAG = "video";
    //午夜视频
    public static final String MIDNIGHT_VIDEO = "movie";
    public static final String MIDNIGHT_VIDEO_TAG = "movie";
    //小说
    public static final String NOVEL_LIST = "novel";
    public static final String NOVEL_LIST_TAG = "novel";
    //小说详情
    public static final String NOVEL_DETAIL = "novel/chapter";
    public static final String NOVEL_DETAIL_TAG = "novel/chapter";
    //收藏小说
    public static final String FOLLOW_NOVEL = "personal/favor/add_novel";
    public static final String FOLLOW_NOVEL_TAG = "personal/favor/add_novel";
    //取消收藏
    public static final String CANCEL_FOLLOW = "/personal/favor/delete";
    public static final String CANCEL_FOLLOW_TAG = "/personal/favor/delete";
    //我收藏的小说
    public static final String MY_FOLLOW_NOVEL="personal/favor/novel";
    public static final String MY_FOLLOW_NOVEL_TAG="personal/favor/novel";

    /**
     * Case By:创建参数基础信息
     * Author: scene on 2017/5/19 13:19
     */
    public static HashMap<String, String> createParams() {
        HashMap<String, String> params = new HashMap<>();
        long timestamp = NetTimeUtils.getWebsiteDatetime();
        params.put("resource_id", MyApplication.getInstance().getResourceId());
        params.put("uuid", MyApplication.getInstance().getImei());
        params.put("timestamp", timestamp + "");
        params.put("signature", getSign(timestamp + ""));
        params.put("app_type", "1");
        params.put("version", AppUtils.getAppVersionCode() + "");
        if (MyApplication.getInstance().getUserInfo() != null) {
            params.put("user_id", MyApplication.getInstance().getUserInfo().getId() + "");
        }
        return params;
    }

    /**
     * Case By:获取sign
     * Author: scene on 2017/5/19 13:19
     */
    private static String getSign(String timestamp) {
        return MD5Util.string2Md5(MD5Util.string2Md5(MyApplication.getInstance().getImei() + 1 + MyApplication.getInstance().getResourceId() + timestamp + AppUtils.getAppVersionCode(), "UTF-8") + SIGN_KEY, "UTF-8");
    }
}
