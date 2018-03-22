package com.quduo.welfareshop.ui.red.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.quduo.welfareshop.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author:scene
 * Time:2018/3/19 17:14
 * Description:积分不足
 */

public class NeedGetDiamondDialog extends Dialog {
    private OnClickGetDiamondListener onClickGetDiamondListener;

    public NeedGetDiamondDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
    }

    public NeedGetDiamondDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected NeedGetDiamondDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setOnClickGetDiamondListener(OnClickGetDiamondListener onClickGetDiamondListener) {
        this.onClickGetDiamondListener = onClickGetDiamondListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_need_get_score);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
    }

    @OnClick(R.id.get_score)
    public void onClickGetScore() {
        if (onClickGetDiamondListener != null) {
            onClickGetDiamondListener.onClickGetDiamond();
        }
        dismiss();
    }

    public interface OnClickGetDiamondListener {
        void onClickGetDiamond();
    }
}
