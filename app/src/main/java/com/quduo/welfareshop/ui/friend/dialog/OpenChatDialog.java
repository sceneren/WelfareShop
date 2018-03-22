package com.quduo.welfareshop.ui.friend.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/3/7 16:16
 * Description:开通私聊
 */

public class OpenChatDialog extends Dialog {

    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.open)
    TextView open;
    private OnClickOpenChatListener onClickOpenChatListener;

    public OpenChatDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
    }

    public OpenChatDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected OpenChatDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setOnClickOpenChatListener(OnClickOpenChatListener onClickOpenChatListener) {
        this.onClickOpenChatListener = onClickOpenChatListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_open_chat);
        ButterKnife.bind(this);
        price.setText(MessageFormat.format("{0}积分", MyApplication.getInstance().getConfigInfo().getChat_price()));
    }

    @Override
    public void show() {
        setCanceledOnTouchOutside(false);
        super.show();
        try {
            Window window = getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = PtrLocalDisplay.SCREEN_WIDTH_PIXELS;                     //使用这种方式更改了dialog的框宽
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.pop_animation);
            window.setAttributes(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.open)
    public void onClickOpen() {
        if (onClickOpenChatListener != null) {
            onClickOpenChatListener.onClickOpenChat();
        }
        dismiss();
    }


    public interface OnClickOpenChatListener {
        void onClickOpenChat();
    }
}
