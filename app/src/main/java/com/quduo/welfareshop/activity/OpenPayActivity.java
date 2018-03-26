package com.quduo.welfareshop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseActivity;
import com.quduo.welfareshop.widgets.Html5Webview;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/3/26 13:22
 * Description:跳转到支付界面的媒介
 */

public class OpenPayActivity extends BaseActivity {
    public static final String ARG_URL = "url";
    @BindView(R.id.webView)
    Html5Webview webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_pay);
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra(ARG_URL);
        if (StringUtils.isTrimEmpty(url)) {
            onBackPressed();
            ToastUtils.showShort("支付路径不正确请重新发起支付");
            return;
        }
        webView.loadUrl(url);
    }
}
