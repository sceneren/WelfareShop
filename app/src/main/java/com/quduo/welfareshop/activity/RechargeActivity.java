package com.quduo.welfareshop.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/3/5 17:50
 * Description:充值
 */

public class RechargeActivity extends BaseMvpActivity<IRechargeView, RechargePresenter> implements IRechargeView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.wechat_pay)
    TextView wechatPay;
    Unbinder unbinder;
    @BindView(R.id.layout_money_1)
    LinearLayout layoutMoney1;
    @BindView(R.id.layout_money_2)
    LinearLayout layoutMoney2;
    @BindView(R.id.layout_money_3)
    LinearLayout layoutMoney3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        unbinder = ButterKnife.bind(this);
        initToolBar();
        initView();
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

    }

    @Override
    public void showLoadingPage() {

    }

    @Override
    public void showContentPage() {

    }

    @Override
    public void showErrorPage() {

    }

    @OnClick(R.id.layout_money_1)
    public void onClickMoney1() {
        layoutMoney1.setBackgroundResource(R.drawable.bg_recharge_money_s);
        layoutMoney2.setBackgroundResource(R.drawable.bg_recharge_money_d);
        layoutMoney3.setBackgroundResource(R.drawable.bg_recharge_money_d);
    }

    @OnClick(R.id.layout_money_2)
    public void onClickMoney2() {
        layoutMoney1.setBackgroundResource(R.drawable.bg_recharge_money_d);
        layoutMoney2.setBackgroundResource(R.drawable.bg_recharge_money_s);
        layoutMoney3.setBackgroundResource(R.drawable.bg_recharge_money_d);
    }

    @OnClick(R.id.layout_money_3)
    public void onClickMoney3() {
        layoutMoney1.setBackgroundResource(R.drawable.bg_recharge_money_d);
        layoutMoney2.setBackgroundResource(R.drawable.bg_recharge_money_d);
        layoutMoney3.setBackgroundResource(R.drawable.bg_recharge_money_s);
    }

    @Override
    public RechargePresenter initPresenter() {
        return new RechargePresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
