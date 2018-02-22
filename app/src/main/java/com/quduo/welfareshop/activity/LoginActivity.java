package com.quduo.welfareshop.activity;

import android.content.Intent;

import com.quduo.welfareshop.base.BaseActivity;
import com.umeng.socialize.UMShareAPI;

/**
 * Author:scene
 * Time:2018/2/22 12:01
 * Description:登录
 */

public class LoginActivity extends BaseActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //三方登录的回调
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
