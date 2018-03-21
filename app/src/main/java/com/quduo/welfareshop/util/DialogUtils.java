package com.quduo.welfareshop.util;

import android.app.Activity;
import android.content.Intent;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.RechargeActivity;
import com.quduo.welfareshop.base.UnlockLisenter;

/**
 * Author:scene
 * Time:2018/3/21 9:36
 * Description:通用弹窗
 */

public class DialogUtils {
    private static volatile DialogUtils instance = null;

    public DialogUtils() {
    }

    public static DialogUtils getInstance() {
        if (instance == null) {
            synchronized (DialogUtils.class) {
                if (instance == null) {
                    instance = new DialogUtils();
                }
            }
        }
        return instance;
    }

    //显示需要解锁的Dialog
    public void showNeedUnlockDialog(final Activity activity, final int needPrice, final int currentScore, final UnlockLisenter unlockLisenter) {
        try {
            if (MyApplication.getInstance().getUserInfo().getScore() < needPrice) {
                showNeedRechargeScoreDialog(activity, needPrice, currentScore);
            } else {
                StyledDialog.buildIosAlert("消耗" + needPrice + "积分查看", "您的当前积分：" + currentScore, new MyDialogListener() {
                    @Override
                    public void onFirst() {
                        if (MyApplication.getInstance().getUserInfo().getScore() < needPrice) {
                            //充值
                            showNeedRechargeScoreDialog(activity, needPrice, currentScore);
                        } else {
                            //解锁
                            unlockLisenter.unlock();
                        }
                    }

                    @Override
                    public void onSecond() {

                    }
                }).setBtnText("确定", "取消").show();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showNeedRechargeScoreDialog(final Activity activity, int needPrice, int currentScore) {
        try {
            StyledDialog.buildIosAlert("消耗" + needPrice + "积分查看", "您当前的积分：" + currentScore + "\n积分不足，请充值", new MyDialogListener() {
                @Override
                public void onFirst() {
                    toRechargeActivity(activity);
                }

                @Override
                public void onSecond() {

                }
            }).setBtnText("充值").show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toRechargeActivity(Activity activity) {
        Intent intent = new Intent(activity, RechargeActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }
}
