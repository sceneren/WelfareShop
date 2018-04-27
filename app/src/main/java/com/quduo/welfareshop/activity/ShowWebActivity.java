package com.quduo.welfareshop.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.hss01248.dialog.StyledDialog;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseBackActivity;
import com.quduo.welfareshop.widgets.X5WebView;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShowWebActivity extends BaseBackActivity {
    public static final String ARG_URL = "url";
    public static final String ARG_POSITION = "position";

    @BindView(R.id.webView)
    X5WebView webView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_web);
        unbinder = ButterKnife.bind(this);
        initToolbar();
        initView();
        MyApplication.getInstance().uploadPageInfo(getIntent().getIntExtra(ARG_POSITION, 57), 0);
    }

    private void initToolbar() {
        toolbarTitle.setText("图文教程");
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back_black);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void initView() {
        String url = getIntent().getStringExtra(ARG_URL);
        webView.postDelayed(new Runnable() {
            @Override
            public void run() {
                StyledDialog.buildLoading().setActivity(ShowWebActivity.this).show();
            }
        }, 200);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                dismissLoading();
            }
        });
        webView.loadUrl(url);
    }

    private void dismissLoading() {
        try {
            webView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    StyledDialog.dismissLoading();
                }
            }, 200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        try {
            webView.stopLoading();
            webView.removeAllViews();
            webView.destroy();
            webView = null;
            dismissLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
        unbinder.unbind();
    }
}
