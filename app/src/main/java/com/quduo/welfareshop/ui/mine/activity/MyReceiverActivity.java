package com.quduo.welfareshop.ui.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.mine.entity.ReceiverInfo;
import com.quduo.welfareshop.ui.mine.presenter.MyReceiverPresenter;
import com.quduo.welfareshop.ui.mine.view.IMyReceiverView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/3/1 14:10
 * Description:收货地址
 */

public class MyReceiverActivity extends BaseMvpActivity<IMyReceiverView, MyReceiverPresenter> implements IMyReceiverView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;
    @BindView(R.id.receiver_name)
    EditText receiverName;
    @BindView(R.id.receiver_phone)
    EditText receiverPhone;
    @BindView(R.id.receiver_address)
    EditText receiverAddress;
    private boolean isFromUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_receiver);
        unbinder = ButterKnife.bind(this);
        initToolbar();
        initView();
        isFromUpdate = getIntent().getBooleanExtra("isFromUpdate", false);
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
        toolbarTitle.setText("收货信息");
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public void initView() {
        presenter.getData();
    }

    @Override
    public MyReceiverPresenter initPresenter() {
        return new MyReceiverPresenter(this);
    }

    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(ApiUtil.RECEIVER_ADDRESS_TAG);
        OkGo.getInstance().cancelTag(ApiUtil.EDIT_RECEIVER_ADDRESS_TAG);
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void showLaodingDialog() {
        try {
            StyledDialog.buildLoading().setActivity(MyReceiverActivity.this).show();
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
    public void showMessage(String message) {
        try {
            ToastUtils.showShort(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bindData(ReceiverInfo data) {
        try {
            if (data != null) {
                receiverName.setText(data.getName());
                receiverPhone.setText(data.getMobile());
                receiverAddress.setText(data.getAddress());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return receiverName.getText().toString().trim();
    }

    @Override
    public String getPhone() {
        return receiverPhone.getText().toString().trim();
    }

    @Override
    public String getAddress() {
        return receiverAddress.getText().toString().trim();
    }

    @Override
    public void bindSuccess() {
        try {
            if (isFromUpdate) {
                Intent intent = new Intent();
                intent.putExtra("name", getName());
                intent.putExtra("address", getAddress());
                intent.putExtra("phone", getPhone());
                setResult(RESULT_OK, intent);
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.save)
    public void onClickSave() {
        presenter.editReceiver();
    }
}
