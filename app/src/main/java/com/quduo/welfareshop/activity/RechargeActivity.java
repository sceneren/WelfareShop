package com.quduo.welfareshop.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.dialog.GetCouponDialog;
import com.quduo.welfareshop.dialog.RechargeQuestionDialog;
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
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.type_wechat)
    RadioButton typeWechat;
    @BindView(R.id.type_alipay)
    RadioButton typeAlipay;
    @BindView(R.id.recharge_type)
    RadioGroup rechargeType;
    @BindView(R.id.recharge_38)
    TextView recharge38;
    @BindView(R.id.notice)
    TextView notice;
    @BindView(R.id.has_question)
    TextView hasQuestion;

    private RechargeQuestionDialog questionDialog;
    private GetCouponDialog getCouponDialog;

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
        String text = "<font color = '#333333'> 充值豪礼：充值任意金额送</font>"
                + "<font color = '#FF8EAF'>38元</font>"
                + "<font color = '#333333'>商城代金券</font>";
        notice.setText(Html.fromHtml(text));
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

    @Override
    public RechargePresenter initPresenter() {
        return new RechargePresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.has_question)
    public void onClickHasQuestion() {
        if (questionDialog == null) {
            questionDialog = new RechargeQuestionDialog(RechargeActivity.this);
        }
        questionDialog.show();
    }

    @OnClick(R.id.recharge_38)
    void onClickRecharge38() {
        if (getCouponDialog == null) {
            getCouponDialog = new GetCouponDialog(RechargeActivity.this);
        }
        getCouponDialog.show();
    }

}
