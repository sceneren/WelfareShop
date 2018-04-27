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

    public static final String CASH_BANK_BANKNAME = "CASH_BANK_BANKNAME";
    public static final String CASH_BANK_BANKCARD = "CASH_BANK_BANKCARD";
    public static final String CASH_BANK_USERNAME = "CASH_BANK_USERNAME";
    public static final String CASH_ALIPAY_USERNAME = "CASH_ALIPAY_USERNAME";
    public static final String CASH_ALIPAY_ALIPAYCARD = "CASH_ALIPAY_ALIPAYCARD";

    //图库首页
    public static final int POSITION_GELLERY_INDEX = 1;
    //图库分类列表
    public static final int POSITION_GELLERY_CATE = 2;
    //图库详情列表
    public static final int POSITION_GELLERY_DETAIL = 3;
    //小视频
    public static final int POSITION_SMALL_VIDEO = 4;
    //美女视频
    public static final int POSITION_BEAUTY_VIDEO = 5;
    //午夜影院
    public static final int POSITION_MIDNIGHT_VIDEO = 6;
    //视频详情
    public static final int POSITION_VIDEO_DETAIL = 7;
    //小说首页
    public static final int POSITION_NOVEL_INDEX = 8;
    //小说详情
    public static final int POSITION_NOVEL_DETAIL = 9;
    //阅读小说
    public static final int POSITION_NOVEL_READ = 10;
    //商城首页
    public static final int POSITION_SHOP_INDEX = 11;
    //商城分类列表
    public static final int POSITION_SHOP_CATE = 12;
    //商品详情
    public static final int POSITION_SHOP_DETAIL = 13;
    //商品评论列表
    public static final int POSITION_SHOP_COMMENT = 14;
    //客服中心
    public static final int POSITION_SHOP_SERVICE_CENTER = 15;
    //商品类型选择
    public static final int POSITION_SHOP_CHOOSE_MODEL = 16;
    //确认订单
    public static final int POSITION_SHOP_CONFIRM_ORDER = 17;
    //红包首页
    public static final int POSITION_RED_INDEX = 18;
    //未拆开的红包
    public static final int POSITION_UNPARK_RED = 19;
    //红包历史
    public static final int POSITION_RED_HISTORY = 20;
    //拆开红包的结果页
    public static final int POSITION_OPEN_RED_RESULT = 21;
    //提现
    public static final int POSITION_CASH = 22;
    //兑换红包弹窗
    public static final int POSITION_BUY_RED_DIALOG = 23;
    //附近的人
    public static final int POSITION_FRIEND_NEAR = 24;
    //人气榜
    public static final int POSITION_FRIEND_RANK = 25;
    //我的关注
    public static final int POSITION_FRIEND_FOLLOW = 26;
    //消息
    public static final int POSITION_FRIEND_MESSAGE = 27;
    //别人的主页
    public static final int POSITION_FRIEND_OTHERS_INFO = 28;
    //聊天
    public static final int POSITION_FRIEND_CHAT = 29;
    //别人的相册
    public static final int POSITION_FRIEND_ALBUM = 30;
    //我的
    public static final int POSITION_MINE = 31;
    //充值
    public static final int POSITION_RECHARGE = 32;
    //未支付的订单
    public static final int POSITION_MINE_ORDER_UNPAY = 33;
    //未发货的订单
    public static final int POSITION_MINE_ORDER_UNSEND = 34;
    //已发货的订单
    public static final int POSITION_MINE_ORDER_UNRECEIVER = 35;
    //订单详情
    public static final int POSITION_MINE_ORDER_DETAIL = 36;
    //关注的商品
    public static final int POSITION_MINE_FOLLOW_GOODS = 37;
    //关注的视频
    public static final int POSITION_MINE_FOLLOW_VIDEO = 38;
    //关注的图库
    public static final int POSITION_MINE_FOLLOW_GALLERY = 39;
    //关注的小说
    public static final int POSITION_MINE_FOLLOW_NOVEL = 40;
    //我的代金券
    public static final int POSITION_MINE_COUPON = 41;
    //我的收货信息
    public static final int POSITION_MINE_RECEIVER_INFO = 42;
    //用户协议
    public static final int POSITION_MINE_USER_AGREEMENT = 43;
    //我的详细信息
    public static final int POSITION_MINE_MY_DETAIL_INFO = 44;
    //我的相册
    public static final int POSITION_MINE_MY_ALBUM = 45;
    //编辑我的资料
    public static final int POSITION_MINE_EDIT_MY_INFO = 46;
    //视频聊天
    public static final int POSITION_FRIEND_VIDEO_CHAT = 47;
    //待评价的订单
    public static final int POSITION_MINE_ORDER_UNCOMMENT = 48;
    //自拍
    public static final int POSITION_WELFARE_SELFIE = 49;
    //VR
    public static final int POSITION_VR = 50;
    //火辣互动
    public static final int POSITION_FRIEND_INTERACT = 51;
    //热门视频
    public static final int POSITION_FRIEND_HOT_VIDEO = 52;
    //交友视频详情
    public static final int POSITION_FRIEND_VIDEO_DETAIL = 53;
    //代金券使用前的弹窗
    public static final int POSITION_MAIN_SHOW_COUPON = 54;
    //老司机未解锁
    public static final int POSITION_UNTECH = 55;
    //老司机已解锁
    public static final int POSITION_TECHED = 56;
    //android图文详情
    public static final int POSITION_ANDROID_WEB = 57;
    //ios图文详情
    public static final int POSITION_IOS_WEB = 57;
    //电脑图文详情
    public static final int POSITION_PC_WEB = 57;
}
