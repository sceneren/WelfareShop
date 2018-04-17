package com.quduo.welfareshop.ui.shop.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.OpenPayActivity;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.UpdateScoreAndDiamondEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.mine.activity.MyReceiverActivity;
import com.quduo.welfareshop.ui.mine.entity.CheckPayResultInfo;
import com.quduo.welfareshop.ui.mine.activity.OrderDetailActivity;
import com.quduo.welfareshop.ui.shop.dialog.BuySuccessDialog;
import com.quduo.welfareshop.ui.shop.entity.ConfirmOrderResultInfo;
import com.quduo.welfareshop.ui.shop.entity.CreateOrderInfo;
import com.quduo.welfareshop.ui.shop.entity.PayInfo;
import com.quduo.welfareshop.ui.shop.presenter.ConfirmOrderPresenter;
import com.quduo.welfareshop.ui.shop.view.IConfirmOrderView;

import org.greenrobot.eventbus.EventBus;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/3/13 15:35
 * Description:确认订单
 */

public class ConfirmOrderActivity extends BaseMvpActivity<IConfirmOrderView, ConfirmOrderPresenter> implements IConfirmOrderView {
    public static final String ARG_ORDER_INFO = "order_info";

    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.goods_image)
    ImageView goodsImage;
    @BindView(R.id.goods_name)
    TextView goodsName;
    @BindView(R.id.goods_price)
    TextView goodsPrice;
    @BindView(R.id.goods_number)
    TextView goodsNumber;
    @BindView(R.id.goods_model)
    TextView goodsModel;
    @BindView(R.id.receiver_name)
    EditText receiverName;
    @BindView(R.id.receiver_phone)
    EditText receiverPhone;
    @BindView(R.id.receiver_address)
    EditText receiverAddress;
    @BindView(R.id.layout_no_receiver)
    LinearLayout layoutNoReceiver;
    @BindView(R.id.layout_has_receiver)
    LinearLayout layoutHasReceiver;
    @BindView(R.id.total_price)
    TextView totalPrice;
    @BindView(R.id.total_score)
    TextView totalScore;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    @BindView(R.id.show_receiver_name)
    TextView showReceiverName;
    @BindView(R.id.show_receiver_phone)
    TextView showReceiverPhone;
    @BindView(R.id.show_receiver_address)
    TextView showReceiverAddress;
    @BindView(R.id.coupon)
    TextView coupon;
    @BindView(R.id.btn_wechat)
    TextView btnWechat;
    @BindView(R.id.btn_alipay)
    TextView btnAlipay;

    private CreateOrderInfo orderInfo;
    private ConfirmOrderResultInfo resultInfo;

    public BuySuccessDialog buySuccessDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.fragment_shop_confirm_order);
        unbinder = ButterKnife.bind(this);
        orderInfo = (CreateOrderInfo) getIntent().getSerializableExtra(ARG_ORDER_INFO);
        if (orderInfo == null) {
            onBackPressed();
            return;
        }
        initToolbar();
        initView();
        presenter.getData();
    }

    @Override
    public void showLoadingPage() {
        try {
            statusView.showLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showContentPage() {
        try {
            statusView.showContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showErrorPage() {
        try {
            statusView.showFailed(retryListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.getData();
        }
    };

    private void initToolbar() {
        toolbarTitle.setText("确认订单");
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
    }

    private void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_SHOP_CONFIRM_ORDER, orderInfo.getGoodsId());
        GlideApp.with(this)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + orderInfo.getThumb())
                .placeholder(R.drawable.ic_default_shop)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(goodsImage);
        goodsName.setText(orderInfo.getGoodsName());
        goodsNumber.setText(MessageFormat.format("数量:{0}", orderInfo.getChoosedNum()));
        goodsPrice.setText(MessageFormat.format("单价:{0}", orderInfo.getGoodsPrice()));
        Number num = Float.parseFloat(orderInfo.getGoodsPrice()) * 100;
        int giveNum = num.intValue() / 100;
        goodsModel.setText(orderInfo.getChooseModel());
        totalPrice.setText(MessageFormat.format("￥{0}", giveNum * orderInfo.getChoosedNum()));
        totalScore.setText(MessageFormat.format("送{0}积分", giveNum * orderInfo.getChoosedNum()));
    }

    @Override
    public ConfirmOrderPresenter initPresenter() {
        return new ConfirmOrderPresenter(this);
    }

    @Override
    protected void onDestroy() {
        KeyboardUtils.hideSoftInput(ConfirmOrderActivity.this);
        OkGo.getInstance().cancelTag(ApiUtil.ORDER_USER_INFO_TAG);
        OkGo.getInstance().cancelTag(ApiUtil.CREATE_ORDER_TAG);
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void bindData(ConfirmOrderResultInfo data) {
        try {
            resultInfo = data;
            if (null == data.getAddress()) {
                layoutHasReceiver.setVisibility(View.GONE);
                layoutNoReceiver.setVisibility(View.VISIBLE);
            } else {
                layoutHasReceiver.setVisibility(View.VISIBLE);
                layoutNoReceiver.setVisibility(View.GONE);
                showReceiverAddress.setText(data.getAddress().getAddress());
                showReceiverName.setText(data.getAddress().getName());
                showReceiverPhone.setText(data.getAddress().getMobile());
            }

            if (null == data.getCoupon()) {
                coupon.setVisibility(View.GONE);
            } else {
                coupon.setVisibility(View.VISIBLE);
                coupon.setText(MessageFormat.format("(代金券已抵扣{0}元)", data.getCoupon().getCost()));
                Number num = Float.parseFloat(orderInfo.getGoodsPrice()) * 100;
                int giveNum = num.intValue() / 100;
                totalPrice.setText(MessageFormat.format("￥{0}", (giveNum * orderInfo.getChoosedNum() - data.getCoupon().getCost())));
            }
            btnAlipay.setVisibility(data.isAli_pay_enable() ? View.VISIBLE : View.GONE);
            btnWechat.setVisibility(data.isWx_pay_enable() ? View.VISIBLE : View.GONE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMessage(String message) {
        try {
            ToastUtils.showShort(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showLoadingDialog() {
        try {
            StyledDialog.buildLoading().setActivity(ConfirmOrderActivity.this).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideLaodingDialog() {
        try {
            StyledDialog.dismissLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int orderId;

    private int goodsOrderId;

    @Override
    public void createOrderSuccess(PayInfo data) {
        try {
            orderId = data.getOrder_id();
            goodsOrderId = data.getData_id();
            Intent intent = new Intent(ConfirmOrderActivity.this, OpenPayActivity.class);
            intent.putExtra(OpenPayActivity.ARG_URL, data.getUrl());
            if (data.getPay_type() == 1 || data.getPay_type() == 3) {
                intent.putExtra(OpenPayActivity.ARG_TYPE, 1);
            } else {
                intent.putExtra(OpenPayActivity.ARG_TYPE, 2);
            }
            startActivityForResult(intent, 40001);
            overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showBuySuccessDialog() {
        if (buySuccessDialog == null) {
            buySuccessDialog = new BuySuccessDialog(ConfirmOrderActivity.this);
            buySuccessDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    onBackPressed();
                }
            });
        }
        Number num = Float.parseFloat(orderInfo.getGoodsPrice()) * 100;
        int giveNum = num.intValue() / 100;
        buySuccessDialog.show();
        buySuccessDialog.setNumber(giveNum * orderInfo.getChoosedNum());
    }

    @Override
    public void paySuccess(CheckPayResultInfo data) {
        try {
            showBuySuccessDialog();
            MyApplication.getInstance().getUserInfo().setScore(data.getScore());
            MyApplication.getInstance().getUserInfo().setDiamond(data.getDiamond());
            EventBus.getDefault().post(new UpdateScoreAndDiamondEvent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alert(String message) {
        try {
            StyledDialog.buildIosAlert("提示", message, new MyDialogListener() {
                @Override
                public void onFirst() {
                    Intent intent = new Intent(ConfirmOrderActivity.this, OrderDetailActivity.class);
                    intent.putExtra(OrderDetailActivity.ARG_ID, goodsOrderId);
                    startActivity(intent);
                    overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
                    finish();
                }

                @Override
                public void onSecond() {
                    finish();
                }
            })
                    .setBtnText("继续支付", "取消")
                    .setActivity(ConfirmOrderActivity.this)
                    .show()
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            finish();
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.layout_has_receiver)
    public void onClickLayoutHasReceiver() {
        Intent intent = new Intent(ConfirmOrderActivity.this, MyReceiverActivity.class);
        intent.putExtra("isFromUpdate", true);
        startActivityForResult(intent, 10001);
        overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 10001 && data != null) {
            showReceiverName.setText(data.getStringExtra("name"));
            showReceiverAddress.setText(data.getStringExtra("address"));
            showReceiverPhone.setText(data.getStringExtra("phone"));
        } else {
            if (requestCode == 40001) {
                presenter.checkPayResult(orderId);
            }
        }
    }

    @OnClick(R.id.btn_wechat)
    public void onClickBtnWechat() {
        createOrder(1);
    }

    @OnClick(R.id.btn_alipay)
    public void onClickBtnAlipay() {
        createOrder(2);
    }


    private String getReceiverName() {
        try {
            if (resultInfo != null && resultInfo.getAddress() != null) {
                return showReceiverName.getText().toString().trim();
            } else {
                return receiverName.getText().toString().trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getReceiverPhone() {
        try {
            if (resultInfo != null && resultInfo.getAddress() != null) {
                return showReceiverPhone.getText().toString().trim();
            } else {
                return receiverPhone.getText().toString().trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getReceiverAddress() {
        try {
            if (resultInfo != null && resultInfo.getAddress() != null) {
                return showReceiverAddress.getText().toString().trim();
            } else {
                return receiverAddress.getText().toString().trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void createOrder(int type) {
        try {
            String strReceiverName = getReceiverName();
            String strReceiverPhone = getReceiverPhone();
            String strReceiverAddress = getReceiverAddress();
            if (StringUtils.isEmpty(strReceiverName)) {
                showMessage("请输入收货人");
                return;
            }
            if (StringUtils.isEmpty(strReceiverPhone)) {
                showMessage("请输入收货电话");
                return;
            }
            if (!RegexUtils.isMobileSimple(strReceiverPhone)) {
                showMessage("请输入正确的收货电话");
                return;
            }
            if (StringUtils.isEmpty(strReceiverAddress)) {
                showMessage("请输入收货地址");
                return;
            }
            presenter.createOrder(orderInfo.getGoodsId(), orderInfo.getChoosedNum(), orderInfo.getChooseModel(), strReceiverName,
                    strReceiverPhone, strReceiverAddress, resultInfo.getCoupon() != null ? resultInfo.getCoupon().getId() : 0, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
