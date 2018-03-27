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
    //收藏图库
    public static final String FOLLOW_GALLERY = "personal/favor/add_gallery";
    public static final String FOLLOW_GALLERY_TAG = "personal/favor/add_gallery";
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
    public static final String MY_FOLLOW_NOVEL = "personal/favor/novel";
    public static final String MY_FOLLOW_NOVEL_TAG = "personal/favor/novel";
    //附近的人
    public static final String NEAR_LIST = "friend/nearby";
    public static final String NEAR_LIST_TAG = "friend/nearby";
    //人气排行
    public static final String RANK_LIST = "friend/ranking";
    public static final String RANK_LIST_TAG = "friend/ranking";
    //关注用户
    public static final String FOLLOW_USER = "friend/subscribe";
    public static final String FOLLOW_USER_TAG = "friend/subscribe";
    //取消关注用户
    public static final String CENCEL_FOLLOW_USER = "friend/unsubscribe";
    public static final String CENCEL_FOLLOW_USER_TAG = "friend/unsubscribe";
    //关注用户列表
    public static final String FOLLOW_USER_LIST = "personal/subscribe";
    public static final String FOLLOW_USER_LIST_TAG = "personal/subscribe";
    //其他用户详情
    public static final String OTHER_USER_DETAIL_INFO = "friend/profile";
    public static final String OTHER_USER_DETAIL_INFO_TAG = "friend/profile";
    //上传头像
    public static final String UPLOAD_AVATAR = "personal/profile/avatar";
    public static final String UPLOAD_AVATAR_TAG = "personal/profile/avatar";
    //获取自己的用户信息
    public static final String MY_DETAIL_INFO = "personal/profile";
    public static final String MY_DETAIL_INFO_TAG = "personal/profile";
    //上传照片
    public static final String UPLOAD_PHOTO = "personal/profile/add_photo";
    public static final String UPLOAD_PHOTO_TAG = "personal/profile/add_photo";
    //删除照片
    public static final String DELETE_PHOTO = "personal/profile/delete_photo";
    public static final String DELETE_PHOTO_TAG = "personal/profile/delete_photo";
    //修改资料
    public static final String UPDATE_MY_INFO = "personal/profile/edit";
    public static final String UPDATE_MY_INFO_TAG = "personal/profile/edit";
    //收藏视频
    public static final String FOLLOW_VIDEO = "personal/favor/add_video";
    public static final String FOLLOW_VIDEO_TAG = "personal/favor/add_video";
    //我收藏的视频列表
    public static final String MY_FOLLOW_VIDEO = "personal/favor/video";
    public static final String MY_FOLLOW_VIDEO_TAG = "personal/favor/video";
    //点赞1商品  2图库  3视频  4小说
    public static final String ZAN = "personal/good";
    public static final String ZAN_TAG = "personal/good";
    //收货信息
    public static final String RECEIVER_ADDRESS = "personal/address";
    public static final String RECEIVER_ADDRESS_TAG = "personal/address";
    //编辑收货信息
    public static final String EDIT_RECEIVER_ADDRESS = "personal/address/edit";
    public static final String EDIT_RECEIVER_ADDRESS_TAG = "personal/address/edit";
    //设为封面
    public static final String SET_COVER = "personal/profile/set_cover";
    public static final String SET_COVER_TAG = "personal/profile/set_cover";
    //红包首页
    public static final String RED_INDEX = "red_bag";
    public static final String RED_INDEX_TAG = "red_bag";
    //买红包
    public static final String BUY_RED = "red_bag/buy";
    public static final String BUY_RED_TAG = "red_bag/buy";
    //未拆开的红包
    public static final String UNPARK_RED = "personal/red_bag";
    public static final String UNPARK_RED_TAG = "personal/red_bag";
    //拆红包
    public static final String OPEN_RED = "red_bag/open";
    public static final String OPEN_RED_TAG = "red_bag/open";
    //视频详情
    public static final String VIDEO_DETAIL = "video/detail";
    public static final String VIDEO_DETAIL_TAG = "video/detail";
    //收藏的图片
    public static final String MY_FOLLOW_IMAGE = "personal/favor/gallery";
    public static final String MY_FOLLOW_IMAGE_TAG = "personal/favor/gallery";
    //解锁
    public static final String UNLOCK = "personal/unlock";
    public static final String UNLOCK_TAG = "personal/unlock";
    //红包历史
    public static final String RED_HISTORY = "personal/red_bag/history";
    public static final String RED_HISTORY_TAG = "personal/red_bag/history";
    //红包详情
    public static final String RED_DETAIL = "red_bag/detail";
    public static final String RED_DETAIL_TAG = "red_bag/detail";
    //提现
    public static final String CASH = "personal/withdraw/request";
    public static final String CASH_TAG = "personal/withdraw/request";
    //解锁私聊
    public static final String UNLOCK_CHAT = "friend/chat";
    public static final String UNLOCK_CHAT_TAG = "friend/chat";
    //商城首页
    public static final String SHOP_INDEX = "goods";
    public static final String SHOP_INDEX_TAG = "goods";
    //商城分类
    public static final String SHOP_CATE_LIST = "goods/list";
    public static final String SHOP_CATE_LIST_TAG = "goods/list";
    //商品评论
    public static final String GOODS_COMMENT = "goods/comment";
    public static final String GOODS_COMMENT_TAG = "goods/comment";
    //商品详情
    public static final String GOODS_DETAIL = "goods/detail";
    public static final String GOODS_DETAIL_TAG = "goods/detail";
    //收藏商品
    public static final String FOLLOW_GOODS = "personal/favor/add_product";
    public static final String FOLLOW_GOODS_TAG = "personal/favor/add_product";
    //我收藏的商品列表
    public static final String MY_FOLLOW_GOODS = "personal/favor/product";
    public static final String MY_FOLLOW_GOODS_TAG = "personal/favor/product";
    //订单相关的信息
    public static final String ORDER_USER_INFO = "order/info";
    public static final String ORDER_USER_INFO_TAG = "order/info";
    //我的代金券
    public static final String MY_COUPON = "personal/coupon";
    public static final String MY_COUPON_TAG = "personal/coupon";
    //创建订单
    public static final String CREATE_ORDER = "order";
    public static final String CREATE_ORDER_TAG = "order";
    //订单列表
    public static final String ORDER_LIST = "personal/order";
    public static final String ORDER_LIST_TAG = "personal/order";
    //取消订单
    public static final String CANCEL_ORDER = "order/cancel";
    public static final String CANCEL_ORDER_TAG = "order/cancel";
    //订单详情
    public static final String ORDER_DETAIL = "personal/order/detail";
    public static final String ORDER_DETAIL_TAG = "personal/order/detail";
    //重新发起支付
    public static final String REPAY_ORDER = "order/pay";
    public static final String REPAY_ORDER_TAG = "order/pay";
    //充值首页
    public static final String RECHARGE_INDEX = "score";
    public static final String RECHARGE_INDEX_TAG = "score";
    //充值
    public static final String RECHARGE = "score/submit";
    public static final String RECHARGE_TAG = "score/submit";
    //视频统计
    public static final String VIDEO_PLAY = "video/play";
    public static final String VIDEO_PLAY_TAG = "video/play";
    //小说统计
    public static final String NOVEL_SEE = "novel/view";
    public static final String NOVEL_SEE_TAG = "novel/view";
    //上传页面统计
    public static final String UPLOAD_PAGE_INFO = "data/position";
    public static final String UPLOAD_PAGE_INFO_TAG = "data/position";
    //启动
    public static final String START_APP = "data/start";
    public static final String START_APP_TAG = "data/start";
    //停留
    public static final String APP_STAY = "data/stay";
    public static final String APP_STAY_TAG = "data/stay";

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
