package com.quduo.welfareshop.ui.friend.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/3/7 16:16
 * Description:积分不足请充值的提示
 */

public class ToRechargeDialog extends Dialog {


    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.to_recharge)
    TextView toRecharge;
    @BindView(R.id.price)
    TextView price;

    private OnClickToRechargeListener onClickToRechargeListener;

    public ToRechargeDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
    }

    public ToRechargeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ToRechargeDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setOnClickToRechargeListener(OnClickToRechargeListener onClickToRechargeListener) {
        this.onClickToRechargeListener = onClickToRechargeListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_to_recharge);
        ButterKnife.bind(this);
        price.setText(MyApplication.getInstance().getConfigInfo().getChat_price() + "积分");
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

    @OnClick(R.id.close)
    public void onClickAll() {
        dismiss();
    }

    @OnClick(R.id.to_recharge)
    public void onClickToRecharge() {
        if (onClickToRechargeListener != null) {
            onClickToRechargeListener.onClickToRecharge();
        }
        dismiss();
    }


    public interface OnClickToRechargeListener {
        void onClickToRecharge();
    }
}
