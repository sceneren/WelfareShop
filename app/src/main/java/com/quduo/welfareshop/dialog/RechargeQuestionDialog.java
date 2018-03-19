package com.quduo.welfareshop.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.ui.shop.fragment.ServiceCenterActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/3/19 15:57
 * Description:充值遇到问题
 */

public class RechargeQuestionDialog extends Dialog {

    public RechargeQuestionDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
    }

    public RechargeQuestionDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected RechargeQuestionDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_recharge_question);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.close)
    void onClickClose() {
        this.cancel();
    }

    @OnClick(R.id.to_service_center)
    void onClickToServiceCenter() {
        try {
            Intent intent = new Intent(getContext(), ServiceCenterActivity.class);
            getContext().startActivity(intent);
            this.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show() {
        super.show();
        try {
            Window window = getWindow();
            if (window != null) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.width = (int) (PtrLocalDisplay.SCREEN_WIDTH_PIXELS * 0.85);
                window.setAttributes(params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
