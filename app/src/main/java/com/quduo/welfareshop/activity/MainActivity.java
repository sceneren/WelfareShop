package com.quduo.welfareshop.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.quduo.welfareshop.MainFragment;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseActivity;
import com.quduo.welfareshop.bean.VersionInfo;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.util.keyboard.OnSoftKeyboardStateChangedListener;

import java.io.File;
import java.util.ArrayList;

import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/1/25 11:58
 * Description:主界面
 */
public class MainActivity extends BaseActivity {
    private ArrayList<OnSoftKeyboardStateChangedListener> mKeyboardStateListeners;      //软键盘状态监听列表
    private ViewTreeObserver.OnGlobalLayoutListener mLayoutChangeListener;
    private boolean mIsSoftKeyboardShowing;

    private static int keyBoardHeight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LogUtils.e("当前渠道号：" + MyApplication.getInstance().getResourceId());
        init();
        keyBoardHeight = SPUtils.getInstance().getInt("KEYBOARD_HEIGHT", 0);
        if (keyBoardHeight == 0) {
            initKeyBoardHeightListener();
        }
        getLocation();
        checkUpdate();
        uploadStayInfo();

        uploadStartApp();
    }

    private void uploadStartApp() {
        HttpParams params = new HttpParams();
        params.put(ApiUtil.createParams());
        OkGo.<String>get(ApiUtil.API_PRE + ApiUtil.START_APP)
                .tag(ApiUtil.START_APP_TAG)
                .params(params)
                .execute(new JsonCallback<String>() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });
    }

    private void init() {
        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }
    }

    public static int getKeyBoardHeight() {
        return keyBoardHeight;
    }

    private void initKeyBoardHeightListener() {
        mIsSoftKeyboardShowing = false;
        mKeyboardStateListeners = new ArrayList<>();
        mLayoutChangeListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //判断窗口可见区域大小
                Rect r = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                //如果屏幕高度和Window可见区域高度差值大于整个屏幕高度的1/3，则表示软键盘显示中，否则软键盘为隐藏状态。
                int heightDifference = PtrLocalDisplay.SCREEN_HEIGHT_PIXELS - (r.bottom - r.top);
                boolean isKeyboardShowing = heightDifference > PtrLocalDisplay.SCREEN_HEIGHT_PIXELS / 3;
                keyBoardHeight = heightDifference;
                //如果之前软键盘状态为显示，现在为关闭，或者之前为关闭，现在为显示，则表示软键盘的状态发生了改变
                if ((mIsSoftKeyboardShowing && !isKeyboardShowing) || (!mIsSoftKeyboardShowing && isKeyboardShowing)) {
                    mIsSoftKeyboardShowing = isKeyboardShowing;
                    SPUtils.getInstance().put("KEYBOARD_HEIGHT", keyBoardHeight);
                    for (int i = 0; i < mKeyboardStateListeners.size(); i++) {
                        OnSoftKeyboardStateChangedListener listener = mKeyboardStateListeners.get(i);
                        listener.OnSoftKeyboardStateChanged(mIsSoftKeyboardShowing, heightDifference);
                    }
                    removeKeyBoardHeightListener();
                }
            }
        };
        //注册布局变化监听
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(mLayoutChangeListener);
    }

    @Override
    public void onBackPressedSupport() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    @Override
    protected void onDestroy() {
        removeKeyBoardHeightListener();
        super.onDestroy();
    }


    private void removeKeyBoardHeightListener() {
        //移除布局变化监听
        try {
            if (mLayoutChangeListener != null) {
                getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(mLayoutChangeListener);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    private void getLocation() {
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        option.setCoorType("bd09ll");
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            MyApplication.getInstance().setLatitude(location.getLatitude());
            MyApplication.getInstance().setLongitude(location.getLongitude());
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            String coorType = location.getCoorType();
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            int errorCode = location.getLocType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("CheckResult")
    private void uploadStayInfo() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {    //无限循环发事件
                    Thread.sleep(60000);
                    emitter.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        HttpParams params = new HttpParams();
                        OkGo.<String>get(ApiUtil.API_PRE + ApiUtil.APP_STAY)
                                .tag(ApiUtil.APP_STAY_TAG)
                                .params(params)
                                .execute(new JsonCallback<String>() {
                                    @Override
                                    public void onSuccess(Response<String> response) {

                                    }
                                });
                    }
                });


    }

    private boolean isExit = true;

    //检查更新
    private void checkUpdate() {
        OkGo.<LzyResponse<VersionInfo>>get(ApiUtil.API_PRE + ApiUtil.UPDATE_APP)
                .tag(ApiUtil.UPDATE_APP_TAG)
                .params(ApiUtil.createParams())
                .execute(new JsonCallback<LzyResponse<VersionInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<VersionInfo>> response) {
                        try {
                            if (response.body().data != null) {
                                final VersionInfo versionInfo = response.body().data;
                                needForceUpdate = true;
                                if (versionInfo.getVersion() > AppUtils.getAppVersionCode()) {
                                    Dialog dialog = StyledDialog.buildIosAlert("版本更新", "检查到有新版本", new MyDialogListener() {
                                        @Override
                                        public void onFirst() {
                                            isExit = false;
                                            downloadApk(versionInfo.getUrl());
                                        }

                                        @Override
                                        public void onSecond() {
                                            isExit = true;
                                        }
                                    }).setBtnText("立即更新", "退出").setActivity(MainActivity.this).show();

                                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialog) {
                                            if (isExit && needForceUpdate) {
                                                MyApplication.getInstance().exit();
                                            }
                                        }
                                    });


                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private boolean downloadFlag = false;
    private boolean needForceUpdate = true;

    private void downloadApk(String apkUrl) {
        try {
            Dialog dialog = StyledDialog.buildLoading("正在下载更新").setActivity(MainActivity.this).show();
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    if (!downloadFlag && needForceUpdate) {
                        MyApplication.getInstance().exit();
                    }
                }
            });
            OkGo.<File>get(apkUrl)
                    .tag(this)
                    .execute(new FileCallback() {
                        @Override
                        public void onStart(Request<File, ? extends Request> request) {
                            super.onStart(request);
                            downloadFlag = false;
                        }

                        @Override
                        public void onSuccess(Response<File> response) {
                            try {
                                LogUtils.e(AppUtils.getAppPackageName() + ".provider");
                                AppUtils.installApp(response.body(), AppUtils.getAppPackageName() + ".provider");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            try {
                                downloadFlag = true;
                                StyledDialog.dismissLoading();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
