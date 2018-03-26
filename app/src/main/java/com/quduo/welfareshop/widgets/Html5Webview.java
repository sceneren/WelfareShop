package com.quduo.welfareshop.widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SizeUtils;
import com.tencent.smtt.sdk.WebChromeClient;


public class Html5Webview extends X5WebView {
    private CustomProgressView progressView;//进度条
    private Context context;

    public Html5Webview(Context context) {
        this(context, null);
    }

    public Html5Webview(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Html5Webview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        //初始化进度条
        progressView = new CustomProgressView(context);
        progressView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SizeUtils.dp2px(4)));
        progressView.setColor(Color.parseColor("#FF8EAF"));
        progressView.setProgress(0);
        //把进度条加到Webview中
        addView(progressView);
        setWebChromeClient(new MyWebCromeClient());
    }


    private class MyWebCromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(com.tencent.smtt.sdk.WebView webView, int newProgress) {
            if (newProgress == 100) {
                //加载完毕进度条消失
                progressView.setVisibility(View.GONE);
            } else {
                //更新进度
                progressView.setProgress(newProgress);
            }
            super.onProgressChanged(webView, newProgress);
        }
    }

}