package com.quduo.welfareshop.ui.red.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.red.entity.CashInfo;
import com.quduo.welfareshop.ui.red.presenter.CashPresenter;
import com.quduo.welfareshop.ui.red.view.ICashView;

import java.text.MessageFormat;

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
    @BindView(R.id.can_cash_money)
    TextView canCashMoney;
    @BindView(R.id.bank_bankname)
    EditText bankBankname;
    @BindView(R.id.bank_bankcard)
    EditText bankBankcard;
    @BindView(R.id.bank_username)
    EditText bankUsername;
    @BindView(R.id.alipay_username)
    EditText alipayUsername;
    @BindView(R.id.alipay_alipay_card)
    EditText alipayAlipayCard;
    @BindView(R.id.cash_cost)
    EditText cashCost;
    @BindView(R.id.brokerage)
    TextView brokerage;

    private int type = 2;

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
        return attachToSwipeBack(view);
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
        brokerage.setText(MessageFormat.format("{0}%", MyApplication.getInstance().getConfigInfo().getWithdraw_fee()));
        canCashMoney.setText(MessageFormat.format("￥{0}", MyApplication.getInstance().getUserInfo().getMoney()));
        getData();
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
            type = 2;
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
            type = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void getData() {
        String bankBankNameStr = SPUtils.getInstance().getString(AppConfig.CASH_BANK_BANKNAME, "");
        String bankBankCardStr = SPUtils.getInstance().getString(AppConfig.CASH_BANK_BANKCARD, "");
        String bankUsernameStr = SPUtils.getInstance().getString(AppConfig.CASH_BANK_USERNAME, "");
        String alipayUsernameStr = SPUtils.getInstance().getString(AppConfig.CASH_ALIPAY_USERNAME, "");
        String alipayAlipayCardStr = SPUtils.getInstance().getString(AppConfig.CASH_ALIPAY_ALIPAYCARD, "");

        bankBankname.setText(bankBankNameStr);
        bankBankcard.setText(bankBankCardStr);
        bankUsername.setText(bankUsernameStr);
        alipayUsername.setText(alipayUsernameStr);
        alipayAlipayCard.setText(alipayAlipayCardStr);
    }

    @Override
    public void showMessage(String message) {
        try {
            StyledDialog.buildIosAlert("提示", message, new MyDialogListener() {
                @Override
                public void onFirst() {

                }

                @Override
                public void onSecond() {

                }
            }).setActivity(_mActivity).setBtnText("确定").show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showLoadingDialog() {
        try {
            StyledDialog.buildLoading().setActivity(_mActivity).show();
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
    public void cashSuccess() {
        try {
            saveData();
            enterCashSuccessFragment();
            cashCost.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getCost() {
        String string = cashCost.getText().toString().trim();
        try {
            return Double.valueOf(string);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public String getBankBankName() {
        return bankBankname.getText().toString().trim();
    }

    @Override
    public String getBankBankCard() {

        return bankBankcard.getText().toString().trim().length() < 8 ? "" : bankBankcard.getText().toString().trim();
    }

    @Override
    public String getBankUsername() {
        return bankUsername.getText().toString().trim();
    }

    @Override
    public String getAlipayUsername() {
        return alipayUsername.getText().toString().trim();
    }

    @Override
    public String getAlipayAlipayCard() {
        return alipayAlipayCard.getText().toString().trim().length() < 6 ? "" : alipayAlipayCard.getText().toString().trim();
    }

    private void enterCashSuccessFragment() {
        try {
            CashInfo cashInfo = new CashInfo();
            cashInfo.setCost(getCost());
            cashInfo.setTime(System.currentTimeMillis());
            cashInfo.setType(type == 1 ? "支付宝" : "银行卡");
            String cashStr = ((type == 1) ? "支付宝" : getBankBankName()) + "\t尾号";
            String lastNumber = (type == 1) ? getAlipayAlipayCard().substring(getAlipayAlipayCard().length() - 4) : getBankBankCard().substring(getBankBankCard().length() - 4);
            cashStr = cashStr + lastNumber;
            cashInfo.setCardString(cashStr);
            start(CashSuccessFragment.newInstance(cashInfo));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        SPUtils.getInstance().getString(AppConfig.CASH_BANK_BANKNAME, getBankBankName());
        SPUtils.getInstance().getString(AppConfig.CASH_BANK_BANKCARD, getBankBankCard());
        SPUtils.getInstance().getString(AppConfig.CASH_BANK_USERNAME, getBankUsername());
        SPUtils.getInstance().getString(AppConfig.CASH_ALIPAY_USERNAME, getAlipayUsername());
        SPUtils.getInstance().getString(AppConfig.CASH_ALIPAY_ALIPAYCARD, getAlipayAlipayCard());
    }

    @OnClick(R.id.cash)
    public void onClickCash() {
        presenter.cash(type);
    }
}
