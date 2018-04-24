package com.quduo.welfareshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.bean.RechargeInfo;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.dialog.EarliestCouponDialog;
import com.quduo.welfareshop.event.UpdateScoreAndDiamondEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.mine.entity.CheckPayResultInfo;
import com.quduo.welfareshop.ui.mine.entity.CouponInfo;
import com.quduo.welfareshop.ui.shop.entity.PayInfo;

import org.greenrobot.eventbus.EventBus;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/3/5 17:50
 * Description:充值
 */

public class RechargeActivity extends BaseMvpActivity<IRechargeView, RechargePresenter> implements IRechargeView {
    public static final String ARG_FROM_POSITION = "arg_from_position";
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.state_money_1)
    ImageView stateMoney1;
    @BindView(R.id.state_money_2)
    ImageView stateMoney2;
    @BindView(R.id.state_money_3)
    ImageView stateMoney3;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;

    private int type = 2;
    private int fromPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        unbinder = ButterKnife.bind(this);
        try {
            fromPosition = getIntent().getIntExtra(ARG_FROM_POSITION, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initToolBar();
        initView();
        //解析本地json数据
        showContentPage();
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_RECHARGE, fromPosition);
    }

    private void initToolBar() {
        toolbarTitle.setText("充值");
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
    }

    private void initView() {
        money.setText(String.valueOf(MyApplication.getInstance().getUserInfo().getScore()));
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

    @Override
    public RechargePresenter initPresenter() {
        return new RechargePresenter(this);
    }

    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(ApiUtil.RECHARGE_INDEX_TAG);
        OkGo.getInstance().cancelTag(ApiUtil.RECHARGE_TAG);
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void bindRechargeListView(RechargeInfo data) {

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
    public void alert(String message) {
        try {
            StyledDialog.buildIosAlert("提示", message, null).setBtnText("确定").setActivity(RechargeActivity.this).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showLoadingDialog() {
        try {
            StyledDialog.buildLoading().setActivity(RechargeActivity.this).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideLoadingDialog() {
        try {
            StyledDialog.dismissLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int orderId;

    @Override
    public void getPayInfoSuccess(PayInfo payInfo) {
        try {
            orderId = payInfo.getOrder_id();
            Intent intent = new Intent(RechargeActivity.this, OpenPayActivity.class);
            intent.putExtra(OpenPayActivity.ARG_URL, payInfo.getUrl());
            if (payInfo.getPay_type() == 1 || payInfo.getPay_type() == 3) {
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
    public void paySuccess(CheckPayResultInfo data) {
        try {
            Intent intent = new Intent(RechargeActivity.this, EarliestCouponDialog.class);
            CouponInfo couponInfo = new CouponInfo();
            couponInfo.setCost(data.getCoupon_cost());
            couponInfo.setExpress_time(data.getCoupon_express_time());
            intent.putExtra(EarliestCouponDialog.ARG_COUPON, couponInfo);
            startActivityForResult(intent, 10001);

            MyApplication.getInstance().getUserInfo().setHas_coupon(true);
            MyApplication.getInstance().getUserInfo().setPayed(true);
            MyApplication.getInstance().getUserInfo().setScore(data.getScore());
            MyApplication.getInstance().getUserInfo().setDiamond(data.getDiamond());
            EventBus.getDefault().post(new UpdateScoreAndDiamondEvent());
            money.setText(MessageFormat.format("{0}积分", MyApplication.getInstance().getUserInfo().getScore()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 40001 && resultCode == RESULT_OK) {
            presenter.checkPayResult(orderId);
            //presenter.getData();
        } else if (resultCode == RESULT_OK && requestCode == 10001) {
            //代金券弹窗返回
            finish();
        }
    }

    @OnClick(R.id.layout_money_1)
    public void onClickLayoutMoney1() {
        if (type == 1) {
            return;
        }
        type = 1;
        setMoneyItemChooseState();
    }

    @OnClick(R.id.layout_money_2)
    public void onClickLayoutMoney2() {
        if (type == 2) {
            return;
        }
        type = 2;
        setMoneyItemChooseState();
    }

    @OnClick(R.id.layout_money_3)
    public void onClickLayoutMoney3() {
        if (type == 3) {
            return;
        }
        type = 3;
        setMoneyItemChooseState();
    }

    @OnClick(R.id.pay_wechat)
    public void rechargeByWechat() {
        presenter.recharge(type, 1, fromPosition);
    }

    @OnClick(R.id.pay_alipay)
    public void rechargeByAlipay() {
        presenter.recharge(type, 2, fromPosition);
    }

    private void setMoneyItemChooseState() {
        stateMoney1.setImageResource(type == 1 ? R.drawable.ic_recharge_money_s : R.drawable.ic_recharge_money_d);
        stateMoney2.setImageResource(type == 2 ? R.drawable.ic_recharge_money_s : R.drawable.ic_recharge_money_d);
        stateMoney3.setImageResource(type == 3 ? R.drawable.ic_recharge_money_s : R.drawable.ic_recharge_money_d);
    }
}
