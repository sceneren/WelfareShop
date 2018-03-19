package com.quduo.welfareshop.ui.red.dialog;

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
 * Time:2018/3/19 17:04
 * Description:获取红包
 */

public class GetRedDialog extends Dialog {
    @BindView(R.id.notice)
    TextView notice;
    @BindView(R.id.number_less)
    ImageView numberLess;
    @BindView(R.id.number)
    TextView numberTv;
    @BindView(R.id.number_add)
    ImageView numberAdd;
    @BindView(R.id.get_red)
    ImageView getRed;

    private int number = 1;

    public GetRedDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
    }

    public GetRedDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected GetRedDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_get_red);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
    }

    @OnClick(R.id.number_less)
    public void onClickNumberLess() {
        try {
            if (number > 1) {
                number -= 1;
                numberTv.setText(String.valueOf(number));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.number_add)
    public void onClickNumberAdd() {
        if (number < 9999) {
            number += 1;
            numberTv.setText(String.valueOf(number));
        }
    }

    @OnClick(R.id.get_red)
    public void onClickGetRed() {
        dismiss();
    }
}
