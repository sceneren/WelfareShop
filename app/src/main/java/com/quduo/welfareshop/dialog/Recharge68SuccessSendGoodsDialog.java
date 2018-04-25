package com.quduo.welfareshop.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Recharge68SuccessSendGoodsDialog extends BaseActivity {
    @BindView(R.id.status_goods_1)
    ImageView statusGoods1;
    @BindView(R.id.status_goods_2)
    ImageView statusGoods2;
    @BindView(R.id.receiver_name)
    EditText receiverName;
    @BindView(R.id.receiver_phone)
    EditText receiverPhone;
    @BindView(R.id.receiver_address)
    EditText receiverAddress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_recharge_68_success_send_goods);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.layout_goods_1)
    public void onClickLayoutGoods1() {
        statusGoods1.setVisibility(View.VISIBLE);
        statusGoods2.setVisibility(View.GONE);
    }

    @OnClick(R.id.layout_goods_2)
    public void onClickLayoutGoods2() {
        statusGoods1.setVisibility(View.GONE);
        statusGoods2.setVisibility(View.VISIBLE);
    }
}
