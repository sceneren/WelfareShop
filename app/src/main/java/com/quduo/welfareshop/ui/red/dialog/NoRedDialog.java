package com.quduo.welfareshop.ui.red.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.quduo.welfareshop.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoRedDialog extends Dialog {
    public NoRedDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
    }

    public NoRedDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected NoRedDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_no_red);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ok)
    public void onClickOK() {
        dismiss();
    }
}
