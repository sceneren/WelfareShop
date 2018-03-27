package com.quduo.welfareshop.ui.mine.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseActivity;
import com.quduo.welfareshop.config.AppConfig;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAgreementActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.content)
    TextView content;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agreement);
        ButterKnife.bind(this);
        toolbarTitle.setText("用户协议");
        toolbarTitle.setText("收货信息");
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        content.setText(MyApplication.getInstance().getConfigInfo().getUser_agreement());
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_MINE_USER_AGREEMENT,0);
    }
}
