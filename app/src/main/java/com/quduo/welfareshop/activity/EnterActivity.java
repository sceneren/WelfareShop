package com.quduo.welfareshop.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseActivity;
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
        HiPermission.create(EnterActivity.this)
                .title("权限申请")
                .style(R.style.PermissionDefaultNormalStyle)
                .permissions(permissons)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                        if (!HiPermission.checkPermission(EnterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            MyApplication.getInstance().exit();
                        }
                    }

                    @Override
                    public void onFinish() {
                        if (HiPermission.checkPermission(EnterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            toMainActivity();
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
