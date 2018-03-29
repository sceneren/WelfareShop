package com.quduo.welfareshop.ui.red.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseBackFragment;
import com.quduo.welfareshop.ui.red.entity.CashInfo;

import org.joda.time.DateTime;

import java.text.MessageFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/3/21 16:58
 * Description:提现成功
 */

public class CashSuccessFragment extends BaseBackFragment {
    private static final String ARG_INFO = "info";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.cash_time)
    TextView cashTime;
    @BindView(R.id.cash_type)
    TextView cashType;
    @BindView(R.id.cash_account)
    TextView cashAccount;
    @BindView(R.id.cash_money)
    TextView cashMoney;
    Unbinder unbinder;

    private CashInfo cashInfo;

    public static CashSuccessFragment newInstance(CashInfo cashInfo) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_INFO, cashInfo);
        CashSuccessFragment fragment = new CashSuccessFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cashInfo = (CashInfo) getArguments().getSerializable(ARG_INFO);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cash_success, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText("账户提现");
        initToolbarNav(toolbar, false);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        DateTime dateTime = new DateTime(cashInfo.getTime() + 24 * 60 * 60 * 1000);
        cashTime.setText(dateTime.toString("yyyy-MM-dd HH:mm"));
        cashAccount.setText(cashInfo.getCardString());
        cashMoney.setText(MessageFormat.format("￥{0}", cashInfo.getCost()));
        cashType.setText(cashInfo.getType());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnConfirm)
    public void onClickConfirm() {
        _mActivity.onBackPressed();
    }
}
