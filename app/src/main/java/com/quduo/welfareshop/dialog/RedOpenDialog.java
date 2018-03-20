package com.quduo.welfareshop.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    private OnClickOpenListener onClickOpenListener;

    public RedOpenDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
    }

    public RedOpenDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RedOpenDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setOnClickOpenListener(OnClickOpenListener onClickOpenListener) {
        this.onClickOpenListener = onClickOpenListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_red_open);
        ButterKnife.bind(this);
        setCanceledOnTouchOutside(false);
    }

    @OnClick(R.id.close)
    void onClickClose() {
        try {
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.open)
    void onClickOpen() {
        if (onClickOpenListener != null) {
            onClickOpenListener.onClickOpen();
        }
    }

    public void showDialog(String total) {
        show();
        totalMoney.setText(MessageFormat.format("奖池总金额：{0}", total));
    }

    public interface OnClickOpenListener {
        void onClickOpen();
    }
}
