package com.quduo.welfareshop.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseActivity;
import com.quduo.welfareshop.bean.LoginInfo;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.util.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

public class EnterActivity extends BaseActivity {
    private long beginTime = 0;
    private static final long MIN_SHOW_TIME = 3 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        beginTime = System.currentTimeMillis();
        MyApplication.getInstance().setResourceId(ResourceUtil.getResouyceId(EnterActivity.this));
        applyExternalStorage();
    }

    //申请内部存储权限
    private void applyExternalStorage() {
        List<PermissionItem> permissons = new ArrayList<>();
        permissons.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "内部存储权限", R.drawable.permission_ic_storage));
        permissons.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "手机唯一标识", R.drawable.permission_ic_phone));
        permissons.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "位置信息", R.drawable.permission_ic_location));
        HiPermission.create(EnterActivity.this)
                .title("权限申请")
                .permissions(permissons)
                .msg("为了正常使用" + getString(R.string.app_name) + ",我们需要这些权限")
                .animStyle(R.style.PermissionAnimScale)
                .style(R.style.PermissionDefaultStyle)
                .filterColor(ContextCompat.getColor(EnterActivity.this, R.color.theme_color))
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                        if (!HiPermission.checkPermission(EnterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            MyApplication.getInstance().exit();
                        }
                        if (!HiPermission.checkPermission(EnterActivity.this, Manifest.permission.READ_PHONE_STATE)) {
                            MyApplication.getInstance().exit();
                        }
                    }

                    @Override
                    public void onFinish() {
                        if (HiPermission.checkPermission(EnterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                                && HiPermission.checkPermission(EnterActivity.this, Manifest.permission.READ_PHONE_STATE)) {
                            login();
                        } else {
                            MyApplication.getInstance().exit();
                        }
                    }

                    @Override
                    public void onDeny(String permission, int position) {
                    }

                    @Override
                    public void onGuarantee(String permission, int position) {

                    }
                });
    }

    private void login() {
        OkGo.<LzyResponse<LoginInfo>>post(ApiUtil.API_PRE + ApiUtil.LOGIN)
                .tag(ApiUtil.LOGIN_TAG)
                .execute(new JsonCallback<LzyResponse<LoginInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<LoginInfo>> response) {
                        try {
                            LoginInfo loginInfo = response.body().data;
                            if (loginInfo != null) {
                                MyApplication.getInstance().setConfigInfo(loginInfo.getConfig());
                                MyApplication.getInstance().setUserInfo(loginInfo.getUser_info());
                                toMainActivity();
                            } else {
                                ToastUtils.showShort("网络连接异常，请检查网络");
                                MyApplication.getInstance().exit();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Response<LzyResponse<LoginInfo>> response) {
                        super.onError(response);
                        try {
                            ToastUtils.showShort("网络连接异常，请检查网络");
                            MyApplication.getInstance().exit();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void toMainActivity() {
        long stayTime = System.currentTimeMillis() - beginTime;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(EnterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }, MIN_SHOW_TIME - stayTime > 0 ? MIN_SHOW_TIME - stayTime : 0);
    }

    @Override
    public void onBackPressedSupport() {
    }
}
