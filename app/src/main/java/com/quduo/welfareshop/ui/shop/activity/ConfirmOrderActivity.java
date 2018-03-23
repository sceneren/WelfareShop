package com.quduo.welfareshop.ui.shop.activity;

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
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.mine.activity.MyReceiverActivity;
import com.quduo.welfareshop.ui.shop.dialog.BuySuccessDialog;
import com.quduo.welfareshop.ui.shop.entity.ConfirmOrderResultInfo;
import com.quduo.welfareshop.ui.shop.entity.CreateOrderInfo;
import com.quduo.welfareshop.ui.shop.presenter.ConfirmOrderPresenter;
import com.quduo.welfareshop.ui.shop.view.IConfirmOrderView;

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
    @BindView(R.id.wechat_pay)
    TextView wechatPay;
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
    private BuySuccessDialog buySuccessDialog;

    private CreateOrderInfo orderInfo;
    private ConfirmOrderResultInfo resultInfo;

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
        toolbarTitle.setText("");
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
    }

    private void initView() {
        GlideApp.with(this)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_shop)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + orderInfo.getThumb())
                .into(goodsImage);
        goodsName.setText(orderInfo.getGoodsName());
        goodsNumber.setText(MessageFormat.format("数量:{0}", orderInfo.getChoosedNum()));
        goodsPrice.setText(MessageFormat.format("单价:{0}", orderInfo.getGoodsPrice()));
        Number num = Float.parseFloat(orderInfo.getGoodsPrice()) * 100;
        int giveNum = num.intValue() / 100;
        goodsModel.setText(orderInfo.getChooseModel());
        totalPrice.setText(MessageFormat.format("￥{0}", giveNum * orderInfo.getChoosedNum()));
        totalScore.setText(MessageFormat.format("送{0}钻石+积分", giveNum * orderInfo.getChoosedNum()));
    }

    @Override
    public ConfirmOrderPresenter initPresenter() {
        return new ConfirmOrderPresenter(this);
    }

    @Override
    protected void onDestroy() {
        KeyboardUtils.hideSoftInput(ConfirmOrderActivity.this);
        OkGo.getInstance().cancelTag(ApiUtil.ORDER_USER_INFO_TAG);
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.wechat_pay)
    public void onClickWeChatPay() {
        if (buySuccessDialog == null) {
            buySuccessDialog = new BuySuccessDialog(ConfirmOrderActivity.this);
        }
        buySuccessDialog.show();
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
        }
    }
}
