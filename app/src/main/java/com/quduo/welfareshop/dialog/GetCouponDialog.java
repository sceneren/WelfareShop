package com.quduo.welfareshop.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.quduo.welfareshop.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/3/19 16:30
 * Description:获得代金券
 */

public class GetCouponDialog extends Dialog {

    @BindView(R.id.cost)
    TextView cost;
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
        setContentView(R.layout.dialog_get_coupon);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.to_shop)
    void onClickToShop() {
        dismiss();
        if (onClickToShopListener != null) {
            onClickToShopListener.onClickToShop();
        }
    }

    @OnClick(R.id.close)
    void onClickClose() {
        dismiss();
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

}
