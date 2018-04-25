package com.quduo.welfareshop.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseActivity;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Recharge98SuccessSendGoodsDialog extends BaseActivity {
    public static final String ARG_RECHARGE_ID = "recharge_id";
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
    private int rechargeId = 0;

    private int giftType = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_recharge_98_success_send_goods);
        ButterKnife.bind(this);
        rechargeId = getIntent().getIntExtra(ARG_RECHARGE_ID, 0);
    }

    @OnClick(R.id.layout_goods_1)
    public void onClickLayoutGoods1() {
        statusGoods1.setVisibility(View.VISIBLE);
        statusGoods2.setVisibility(View.GONE);
        giftType = 3;
    }

    @OnClick(R.id.layout_goods_2)
    public void onClickLayoutGoods2() {
        statusGoods1.setVisibility(View.GONE);
        statusGoods2.setVisibility(View.VISIBLE);
        giftType = 4;
    }

    @OnClick(R.id.confirm)
    public void onClickConfirm() {
        commit();
    }

    public void commit() {
        try {
            String name = receiverName.getText().toString().trim();
            if (StringUtils.isTrimEmpty(name)) {
                ToastUtils.showShort("请输入收货人");
                return;
            }
            String phone = receiverPhone.getText().toString().trim();
            if (StringUtils.isTrimEmpty(phone)) {
                ToastUtils.showShort("请输入收货电话");
                return;
            }
            if (!RegexUtils.isMobileSimple(phone)) {
                ToastUtils.showShort("请输入正确的收货电话");
                return;
            }
            String address = receiverAddress.getText().toString().trim();
            if (StringUtils.isTrimEmpty(address)) {
                ToastUtils.showShort("请输入收货地址");
                return;
            }

            showLoadingDialog();

            HttpParams params = new HttpParams();
            params.put("gift_type", giftType);
            params.put("recharge_id", rechargeId);
            params.put("name", name);
            params.put("mobile", phone);
            params.put("address", address);
            OkGo.<LzyResponse<String>>post(ApiUtil.API_PRE + ApiUtil.RECHARGE_GIFT)
                    .tag(ApiUtil.RECHARGE_GIFT_TAG)
                    .params(params)
                    .execute(new JsonCallback<LzyResponse<String>>() {
                        @Override
                        public void onSuccess(Response<LzyResponse<String>> response) {
                            try {
                                if (response.body().code == 200 && response.body().status) {
                                    ToastUtils.showShort("领取成功");
                                    finish();
                                } else {
                                    ToastUtils.showShort("领取失败请重试");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                try {
                                    ToastUtils.showShort("领取失败请重试");
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onError(Response<LzyResponse<String>> response) {
                            super.onError(response);
                            try {
                                ToastUtils.showShort(response.getException().getMessage());
                            } catch (Exception e) {
                                e.printStackTrace();
                                try {
                                    ToastUtils.showShort("领取失败请重试");
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            hideLoadingDialog();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showLoadingDialog() {
        try {
            StyledDialog.buildLoading().setActivity(Recharge98SuccessSendGoodsDialog.this).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideLoadingDialog() {
        try {
            StyledDialog.dismissLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
