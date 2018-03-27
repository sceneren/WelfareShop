package com.quduo.welfareshop.ui.shop.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.mvp.BaseBackActivity;
import com.quduo.welfareshop.widgets.X5WebView;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/3/9 16:28
 * Description:客服中心
 */

public class ServiceCenterActivity extends BaseBackActivity {
    @BindView(R.id.webView)
    X5WebView webView;
    Unbinder unbinder;

    private ValueCallback<Uri> uploadFile;
    private ValueCallback<Uri[]> uploadFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_service_center);
        unbinder = ButterKnife.bind(this);
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_SHOP_SERVICE_CENTER,0);
        initView();
    }

    @Override
    public void initView() {
        webView.setWebChromeClient(new WebChromeClient() {

            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                Log.i("test", "openFileChooser 3");
                ServiceCenterActivity.this.uploadFile = uploadFile;
                openFileChooseProcess();
            }

            public boolean onShowFileChooser(com.tencent.smtt.sdk.WebView webView,
                                             ValueCallback<Uri[]> filePathCallback,
                                             FileChooserParams fileChooserParams) {
                Log.i("test", "openFileChooser 4:" + filePathCallback.toString());
                ServiceCenterActivity.this.uploadFiles = filePathCallback;
                openFileChooseProcess();
                return true;
            }
        });

        webView.loadUrl("https://kefu.easemob.com/webim/im.html?configId=9fa8657f-f24a-40aa-b385-2008db5c4b62");
    }

    private void openFileChooseProcess() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("*/*");
        startActivityForResult(Intent.createChooser(i, "test"), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    if (null != uploadFile) {
                        Uri result = data == null ? null : data.getData();
                        uploadFile.onReceiveValue(result);
                        uploadFile = null;
                    }
                    if (null != uploadFiles) {
                        Uri result = data == null ? null : data.getData();
                        uploadFiles.onReceiveValue(new Uri[]{result});
                        uploadFiles = null;
                    }
                    break;
                default:
                    break;
            }
        } else if (resultCode == RESULT_CANCELED) {
            if (null != uploadFile) {
                uploadFile.onReceiveValue(null);
                uploadFile = null;
            }
            if (null != uploadFiles) {
                uploadFiles.onReceiveValue(null);
                uploadFiles = null;
            }
        }
    }

    @Override
    protected void onDestroy() {
        webView.destroy();
        super.onDestroy();
        unbinder.unbind();
    }
}
