package com.quduo.welfareshop.ui.shop.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author:scene
 * Time:2018/3/19 16:17
 * Description:购买成功
 */

public class BuySuccessDialog extends Dialog {
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.ok)
    ImageView ok;

    public BuySuccessDialog(@NonNull Context context) {
        super(context,R.style.Dialog);
    }

    public BuySuccessDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BuySuccessDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_buy_success);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ok)
    public void onClickOK() {
        this.dismiss();
    }
}
