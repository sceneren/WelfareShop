package com.quduo.welfareshop.ui.friend.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseActivity;
import com.quduo.welfareshop.ui.mine.activity.EditMyInfoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/3/8 13:10
 * Description:完善资料第一步
 */

public class EditUserInfoStep3Dialog extends BaseActivity {
    Unbinder unbinder;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.complete)
    TextView complete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_edit_userinfo_step_3);
        unbinder = ButterKnife.bind(this);
        setFinishOnTouchOutside(false);
    }

    @OnClick(R.id.next)
    public void onClickNext() {
        Intent intent = new Intent(EditUserInfoStep3Dialog.this, EditMyInfoActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.complete)
    public void onClickComplete() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
