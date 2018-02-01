package com.quduo.welfareshop.activity;

import android.content.Intent;

import com.quduo.welfareshop.base.BaseActivity;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by scene on 2018/2/1.
 */

public class LoginActivity extends BaseActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //三方登录的回调
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
