package com.quduo.welfareshop.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:scene
 * Time:2018/3/7 16:16
 * Description:开红包的dialog
 */

public class RedOpenDialog extends Dialog {


    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.total_money)
    TextView totalMoney;
    @BindView(R.id.open)
    ImageView open;

    public RedOpenDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
    }

    public RedOpenDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RedOpenDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_red_open);
    }

    @OnClick(R.id.close)
    public void onClickClose() {
        try {
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
