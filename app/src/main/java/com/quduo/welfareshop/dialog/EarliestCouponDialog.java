package com.quduo.welfareshop.dialog;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.widget.TextView;

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

public class EarliestCouponDialog extends BaseActivity {
    @BindView(R.id.cost)
    TextView cost;
    @BindView(R.id.time)
    TextView time;

    private CountDownTimer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_earliest_coupon);
        ButterKnife.bind(this);
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_FRIEND_INTERACT, 0);
        getData();
    }


    private void getData() {
        OkGo.<LzyResponse<CouponInfo>>get(ApiUtil.API_PRE + ApiUtil.EARLIEST_COUPON)
                .tag(ApiUtil.EARLIEST_COUPON_TAG)
                .execute(new JsonCallback<LzyResponse<CouponInfo>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<CouponInfo>> response) {
                        try {
                            showCountDownTimer(response.body().data);
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

    private void showCountDownTimer(CouponInfo couponInfo) {
        try {
            cost.setText(String.valueOf(couponInfo.getCost()));
            final long lessTime = couponInfo.getExpress_time() * 1000 - System.currentTimeMillis();
            timer = new CountDownTimer(lessTime, 50) {
                @Override
                public void onTick(long millisUntilFinished) {
                    try {
                        time.setText(formatCountDownTime(lessTime));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        if (timer != null) {
                            timer.cancel();
                            timer = null;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.to_shop)
    public void onClickToShop() {
        try {
            EventBus.getDefault().post(new TabSelectedEvent(2));
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        try {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        OkGo.getInstance().cancelTag(ApiUtil.EARLIEST_COUPON_TAG);
        super.onDestroy();
    }

    private String formatCountDownTime(long mills) {
        if (mills > 0) {
            long day = mills / 86400000;
            long hours = (mills - 86400000 * day) / 3600000;
            long minutes = (mills - 86400000 * day - hours * 3600000) / 60000;
            long second = (mills - 86400000 * day - hours * 3600000 - minutes * 60000) / 1000;
            long mSec = (mills - 86400000 * day - hours * 3600000 - minutes * 60000 - second * 1000) / 10;
            return day + "天" + hours + ":" + minutes + ":" + second + ":" + mSec;
        } else {
            return "0天00:00:00:00";
        }
    }
}
