package com.quduo.welfareshop;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.Utils;
import com.hss01248.dialog.ActivityStackManager;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.MemoryCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.quduo.libselecter.ISNav;
import com.quduo.libselecter.common.ImageLoader;
import com.quduo.welfareshop.activity.MainActivity;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.bean.ConfigInfo;
import com.quduo.welfareshop.bean.UserInfo;
import com.quduo.welfareshop.config.Config;
import com.quduo.welfareshop.greendao.GreenDaoManager;
import com.quduo.welfareshop.recovery.core.Recovery;
import com.quduo.welfareshop.util.PageFactory;
import com.quduo.welfareshop.util.ResourceUtil;
import com.umeng.commonsdk.UMConfigure;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * application
 * Created by scene on 2016/7/1.
 */
public class MyApplication extends LitePalApplication {

    //记录当前栈里所有activity
    private List<Activity> activities = new ArrayList<>();
    //记录需要一次性关闭的页面
    private List<Activity> activitys = new ArrayList<>();
    //资源Id
    private String resourceId;
    //imei
    private String imei;
    //配置信息
    private ConfigInfo configInfo;
    //用户信息
    private UserInfo userInfo;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //初始化异常管理工具
        Recovery.getInstance()
                .debug(true)//关闭后 在错误统一管理页面不显示异常数据
                .recoverInBackground(false)
                .recoverStack(false)
                .mainPage(MainActivity.class)//恢复页面
                .init(this);
        //初始化工具类
        Utils.init(this);
        PtrLocalDisplay.init(this);
        //初始化OKhttp
        initOKhttp();
        //初始化dialog
        initDialog();
        //初始化数据库
        LitePal.initialize(this);
        Config.createConfig(this);
        PageFactory.createPageFactory(this);
        //初始化友盟统计
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        UMConfigure.setLogEnabled(true);
        //初始化数据库
        //初始化greendao
        GreenDaoManager.getInstance();
        initPhotoImageLoader();
    }

    // 自定义图片加载器
    private void initPhotoImageLoader() {
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                GlideApp.with(context).load(path).into(imageView);
            }
        });
    }

    //初始化dialog
    private void initDialog() {
        //初始化dialog
        StyledDialog.init(getApplicationContext());
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ActivityStackManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityStackManager.getInstance().removeActivity(activity);
            }
        });
    }

    /**
     * 应用实例
     **/
    private static MyApplication instance;

    /**
     * 获得实例
     *
     * @return Application
     */
    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 新建了一个activity
     *
     * @param activity activity对象
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity activity对象
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            this.activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /*
    *给临时Activitys
    * 添加activity
    * */
    public void addTemActivity(Activity activity) {
        activitys.add(activity);
    }

    public void finishTemActivity(Activity activity) {
        if (activity != null) {
            this.activitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /*
    * 退出指定的Activity
    * */
    public void exitActivitys() {
        for (Activity activity : activitys) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 应用退出，结束所有的activity
     */
    public void exit() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.exit(0);
    }

    public String getResourceId() {
        if (StringUtils.isTrimEmpty(resourceId)) {
            resourceId = ResourceUtil.getResouyceId(getApplicationContext());
        }
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getImei() {
        if (StringUtils.isTrimEmpty(imei)) {
            imei = ResourceUtil.getImei();
        }
        return imei;
    }

    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

    public void setConfigInfo(ConfigInfo configInfo) {
        this.configInfo = configInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * Case By: 初始化Okhttp
     * package:
     * Author：scene on 2017/6/26 11:55
     */
    private void initOKhttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.WARNING);
        builder.addInterceptor(loggingInterceptor);
        //全局的读取超时时间
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的写入超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //全局的连接超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        //使用内存保持cookie，app退出后，cookie消失
        builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));
        //方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        OkGo.getInstance().init(this)                            //必须调用初始化
                .setOkHttpClient(builder.build())               //必须设置OkHttpClient
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(0);                         //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
    }
}
