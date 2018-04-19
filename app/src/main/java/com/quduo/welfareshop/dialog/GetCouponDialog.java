package com.quduo.welfareshop.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.quduo.welfareshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author:scene
 * Time:2018/3/19 16:30
 * Description:获得代金券
 */

public class GetCouponDialog extends Dialog {

    @BindView(R.id.cost)
    TextView cost;
    @BindView(R.id.time)
    TextView time;
    private CountDownTimer countDownTimer;
    private OnClickToShopListener onClickToShopListener;

    public void setOnClickToShopListener(OnClickToShopListener onClickToShopListener) {
        this.onClickToShopListener = onClickToShopListener;
    }

    public GetCouponDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
    }

    public GetCouponDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected GetCouponDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_earliest_coupon);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.to_shop)
    void onClickToShop() {
        dismiss();
        if (onClickToShopListener != null) {
            onClickToShopListener.onClickToShop();
        }
    }

    @Override
    public void show() {
        super.show();
    }

    public interface OnClickToShopListener {
        void onClickToShop();
    }

    public void showDialog(int costNum) {
        show();
        cost.setText(String.valueOf(costNum));
    }


    @Override
    public void dismiss() {
        try {
            if (countDownTimer != null) {
                countDownTimer.cancel();
                countDownTimer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.dismiss();
    }
}
