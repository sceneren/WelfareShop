package com.quduo.welfareshop.ui.red.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.red.presenter.CashPresenter;
import com.quduo.welfareshop.ui.red.view.ICashView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/3/8 11:29
 * Description:提现
 */

public class CashFragment extends BaseBackMvpFragment<ICashView, CashPresenter> implements ICashView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.line_bank_cark)
    View lineBankCark;
    @BindView(R.id.way_back_card)
    LinearLayout wayBackCard;
    @BindView(R.id.line_alipay)
    View lineAlipay;
    @BindView(R.id.way_alipay)
    LinearLayout wayAlipay;
    @BindView(R.id.layout_bank_cark)
    LinearLayout layoutBankCark;
    @BindView(R.id.layout_alipay)
    LinearLayout layoutAlipay;
    Unbinder unbinder;

    public static CashFragment newInstance() {
        Bundle args = new Bundle();
        CashFragment fragment = new CashFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cash, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
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
    public void initToolbar() {
        toolbarTitle.setText("账户提现");
        initToolbarNav(toolbar, true);
    }

    @Override
    public void initView() {

    }

    @Override
    public CashPresenter initPresenter() {
        return new CashPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.way_back_card)
    public void onClickWayBankCard() {
        showLayoutBankCard();
    }

    @OnClick(R.id.way_alipay)
    public void onClickWayAlipay() {
        showLayoutAlipay();
    }


    private void showLayoutBankCard() {
        try {
            lineAlipay.setBackgroundResource(R.color.bg_color);
            lineBankCark.setBackgroundResource(R.color.theme_color);
            layoutBankCark.setVisibility(View.VISIBLE);
            layoutAlipay.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showLayoutAlipay() {
        try {
            lineAlipay.setBackgroundResource(R.color.theme_color);
            lineBankCark.setBackgroundResource(R.color.bg_color);
            layoutBankCark.setVisibility(View.GONE);
            layoutAlipay.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
