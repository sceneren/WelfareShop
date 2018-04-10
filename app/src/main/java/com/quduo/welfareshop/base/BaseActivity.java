package com.quduo.welfareshop.base;

import com.hss01248.dialog.ActivityStackManager;
import com.umeng.analytics.MobclickAgent;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 基类
 * Created by scene on 2018/1/23.
 */

public class BaseActivity extends SupportActivity {
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ActivityStackManager.getInstance().setTopAttached(this);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActivityStackManager.getInstance().removeTopAttached(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
