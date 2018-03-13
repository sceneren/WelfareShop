package com.quduo.welfareshop.ui.shop.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.shop.presenter.ConfirmOrderPresenter;
import com.quduo.welfareshop.ui.shop.view.IConfirmOrderView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/3/13 15:35
 * Description:确认订单
 */

public class ConfirmOrderActivity extends BaseMvpActivity<IConfirmOrderView, ConfirmOrderPresenter> implements IConfirmOrderView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.goods_image)
    ImageView goodsImage;
    @BindView(R.id.goods_name)
    TextView goodsName;
    @BindView(R.id.goods_number)
    TextView goodsNumber;
    @BindView(R.id.layout_no_receiver)
    LinearLayout layoutNoReceiver;
    @BindView(R.id.layout_has_receiver)
    LinearLayout layoutHasReceiver;
    @BindView(R.id.wechat_pay)
    TextView wechatPay;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.fragment_shop_confirm_order);
        unbinder = ButterKnife.bind(this);
        initToolbar();
        initView();
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
        showContentPage();
    }

    @Override
    public ConfirmOrderPresenter initPresenter() {
        return new ConfirmOrderPresenter(this);
    }

    @Override
    protected void onDestroy() {
        KeyboardUtils.hideSoftInput(ConfirmOrderActivity.this);
        super.onDestroy();
        unbinder.unbind();
    }

}
