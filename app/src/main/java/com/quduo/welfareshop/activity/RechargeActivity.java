package com.quduo.welfareshop.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.adapter.RechargeAdapter;
import com.quduo.welfareshop.bean.RechargeInfo;
import com.quduo.welfareshop.bean.RechargeTypeInfo;
import com.quduo.welfareshop.dialog.GetCouponDialog;
import com.quduo.welfareshop.dialog.RechargeQuestionDialog;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.shop.entity.PayInfo;
import com.quduo.welfareshop.widgets.CustomListView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

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
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.notice)
    TextView notice;
    @BindView(R.id.has_question)
    TextView hasQuestion;
    @BindView(R.id.type_wechat)
    TextView typeWechat;
    @BindView(R.id.type_wechat_line)
    View typeWechatLine;
    @BindView(R.id.layout_type_wechat)
    RelativeLayout layoutTypeWechat;
    @BindView(R.id.type_alipay)
    TextView typeAlipay;
    @BindView(R.id.type_alipay_line)
    View typeAlipayLine;
    @BindView(R.id.layout_type_alipay)
    RelativeLayout layoutTypeAlipay;
    @BindView(R.id.listView)
    CustomListView listView;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;

    private RechargeQuestionDialog questionDialog;
    private GetCouponDialog getCouponDialog;

    private List<RechargeTypeInfo> list = new ArrayList<>();
    private RechargeAdapter adapter;

    private int payType = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        unbinder = ButterKnife.bind(this);
        initToolBar();
        initView();
        presenter.getData();
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
        initListView();
    }

    private void initListView() {
        adapter = new RechargeAdapter(RechargeActivity.this, list);
        listView.setAdapter(adapter);
        adapter.setOnClickRechargeListener(new RechargeAdapter.OnClickRechargeListener() {
            @Override
            public void onClickRecharge(int position) {
                presenter.recharge(list.get(position).getType(), payType);
            }
        });
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

    @OnClick(R.id.has_question)
    public void onClickHasQuestion() {
        if (questionDialog == null) {
            questionDialog = new RechargeQuestionDialog(RechargeActivity.this);
        }
        questionDialog.show();
    }

    @OnClick(R.id.layout_type_wechat)
    public void onClickTypeWechat() {
        typeWechat.setTextColor(Color.parseColor("#1CBA25"));
        typeWechatLine.setBackgroundColor(Color.parseColor("#1CBA25"));
        typeAlipay.setTextColor(Color.parseColor("#333333"));
        typeAlipayLine.setBackgroundColor(Color.parseColor("#00000000"));
        payType = 1;
    }

    @OnClick(R.id.layout_type_alipay)
    public void onClickTypeAlipay() {
        typeWechat.setTextColor(Color.parseColor("#333333"));
        typeWechatLine.setBackgroundColor(Color.parseColor("#00000000"));
        typeAlipay.setTextColor(Color.parseColor("#2FB7FE"));
        typeAlipayLine.setBackgroundColor(Color.parseColor("#2FB7FE"));
        payType = 2;
    }

    @Override
    public void bindRechargeListView(RechargeInfo data) {
        try {
            list.clear();
            list.addAll(data.getScore_recharge_type());
            adapter.notifyDataSetChanged();
            money.setText(MessageFormat.format("{0}积分", data.getScore()));
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
    public void alert(String message) {
        try {
            StyledDialog.buildIosAlert("提示", message, null).setActivity(RechargeActivity.this).show();
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

    @Override
    public void getPayInfoSuccess(PayInfo payInfo) {
        try {
            Intent intent = new Intent(RechargeActivity.this, OpenPayActivity.class);
            intent.putExtra(OpenPayActivity.ARG_URL, payInfo.getUrl());
            startActivityForResult(intent, 1);
            overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        showCouponDialog();
    }

    private void showCouponDialog() {
        if (getCouponDialog == null) {
            getCouponDialog = new GetCouponDialog(RechargeActivity.this);
            getCouponDialog.setOnClickToShopListener(new GetCouponDialog.OnClickToShopListener() {
                @Override
                public void onClickToShop() {
                    onBackPressed();
                }
            });
        }
        getCouponDialog.show();
    }
}
