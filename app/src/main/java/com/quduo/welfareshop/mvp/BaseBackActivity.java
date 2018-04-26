package com.quduo.welfareshop.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.quduo.welfareshop.R;

import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

/**
 * Case By: 基类
 * package:
 * Author：scene on 2017/6/27 10:52
 */
public abstract class BaseBackActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
    }

    public abstract void initView();

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }
}
