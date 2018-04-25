package com.quduo.welfareshop.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseActivity;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.TabSelectedEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.http.base.LzyResponse;
import com.quduo.welfareshop.http.callback.JsonCallback;
import com.quduo.welfareshop.ui.mine.entity.CouponInfo;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;

public class Coupon38Dialog extends BaseActivity {
    public static final String ARG_COUPON = "coupon";
    @BindView(R.id.count_down_view)
    CountdownView countDownView;
    @BindView(R.id.confirm)
    ImageView confirm;
    private CouponInfo couponInfo = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_coupon_38);
        ButterKnife.bind(this);
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_FRIEND_INTERACT, 0);
        couponInfo = (CouponInfo) getIntent().getSerializableExtra(ARG_COUPON);
        if (couponInfo == null) {
            confirm.setImageResource(R.drawable.ic_to_go_shop);
            getData();
        } else {
            confirm.setImageResource(R.drawable.btn_conpon_38dialog_confrim);
            countDownView.start(couponInfo.getExpress_time() * 1000 - System.currentTimeMillis());
        }
    }

    @OnClick(R.id.confirm)
    public void onClickConfirm() {
        if (couponInfo == null) {
            EventBus.getDefault().post(new TabSelectedEvent(2));
            setResult(RESULT_OK);
            finish();
        } else {
            finish();
        }
    }

    private void getData() {
        OkGo.<LzyResponse<CouponInfo>>get(ApiUtil.API_PRE + ApiUtil.EARLIEST_COUPON)
                .tag(ApiUtil.EARLIEST_COUPON_TAG)
                .execute(new JsonCallback<LzyResponse<CouponInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<CouponInfo>> response) {
                        try {
                            countDownView.start(response.body().data.getExpress_time()*1000 - System.currentTimeMillis());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<LzyResponse<CouponInfo>> response) {
                        super.onError(response);
                        try {
                            ToastUtils.showShort("暂无可用的代金券");
                            finish();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    @OnClick(R.id.confirm)
    public void onClickToShop() {
        try {
            if(couponInfo!=null){
                finish();
            }else{
                EventBus.getDefault().post(new TabSelectedEvent(2));
                setResult(RESULT_OK);
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(ApiUtil.EARLIEST_COUPON_TAG);
        super.onDestroy();
    }
}
