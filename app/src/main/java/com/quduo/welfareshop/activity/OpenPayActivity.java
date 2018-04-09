package com.quduo.welfareshop.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/3/26 13:22
 * Description:跳转到支付界面的媒介
 */

public class OpenPayActivity extends BaseActivity {
    public static final String ARG_URL = "url";
    @BindView(R.id.webView)
    WebView webView;

    public static final String ARG_TYPE = "type";

    private int type = 1;

    private boolean isNeedFinish = false;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_pay);
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra(ARG_URL);
        type = getIntent().getIntExtra(ARG_TYPE, 1);
        if (StringUtils.isTrimEmpty(url)) {
            onBackPressed();
            ToastUtils.showShort("支付路径不正确请重新发起支付");
            return;
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                //simulateClick();
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (type == 1) {
                    if (url.startsWith("weixin:")) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                        isNeedFinish = true;
                        return true;
                    }
                    return super.shouldOverrideUrlLoading(view, url);
                } else {
                    if (webView != null && !TextUtils.isEmpty(url)) {
                        if (url.contains("platformapi/startapp")) {
                            startAlipayActivity(url);
                            // android  6.0 两种方式获取intent都可以跳转支付宝成功,7.1测试不成功
                        } else if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                                && (url.contains("platformapi") && url.contains("startapp"))) {
                            startAlipayActivity(url);
                        } else {
                            webView.loadUrl(url);
                        }
                    } else {
                        finish();
                    }
                    return true;
                }

            }
        });

        if (url.startsWith("weixin:")) {
            if (type == 1) {
                try {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    isNeedFinish = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtils.showShort("请先安装或打开微信");
                }
            }
        } else {
            webView.loadUrl(url);
        }

    }

    private void simulateClick() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                float x = PtrLocalDisplay.SCREEN_WIDTH_PIXELS / 2;
                float y = PtrLocalDisplay.SCREEN_HEIGHT_PIXELS / 2;
                String[] order = {
                        "input",
                        "tap",
                        "" + x,
                        "" + y
                };
                try {
                    new ProcessBuilder(order).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 50);

    }

    // 调起支付宝并跳转到指定页面
    private void startAlipayActivity(String url) {
        Intent intent;
        try {
            intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setComponent(null);
            startActivity(intent);
            isNeedFinish = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNeedFinish) {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        isNeedFinish = true;
    }

    @Override
    protected void onDestroy() {
        try {
            webView.stopLoading();
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
