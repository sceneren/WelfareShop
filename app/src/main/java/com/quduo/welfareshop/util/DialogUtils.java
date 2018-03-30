package com.quduo.welfareshop.util;

import android.app.Activity;
import android.content.Intent;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.RechargeActivity;
import com.quduo.welfareshop.base.UnlockLisenter;
import com.quduo.welfareshop.ui.friend.activity.VideoChatActivity;
import com.quduo.welfareshop.ui.friend.dialog.OpenChatDialog;
import com.quduo.welfareshop.ui.friend.dialog.ToRechargeDialog;
import com.quduo.welfareshop.ui.friend.dialog.VideoChatNoticeDialog;
import com.quduo.welfareshop.ui.friend.dialog.VideoChatToRechargeDialog;

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
            StyledDialog.buildIosAlert("消耗" + needPrice + "积分查看", "您当前的积分：" + currentScore + "\n\n积分不足，请先充值", new MyDialogListener() {
                @Override
                public void onFirst() {
                    toRechargeActivity(activity);
                }

                @Override
                public void onSecond() {

                }
            }).setBtnText("去充值").show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toRechargeActivity(Activity activity) {
        Intent intent = new Intent(activity, RechargeActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }


    //解锁私聊
    public void showUnlockChatDialog(Activity activity, final UnlockLisenter unlockLisenter) {
        try {
            if (MyApplication.getInstance().getUserInfo().getScore() >= MyApplication.getInstance().getConfigInfo().getChat_price()) {

                OpenChatDialog openChatDialog = new OpenChatDialog(activity);
                openChatDialog.setOnClickOpenChatListener(new OpenChatDialog.OnClickOpenChatListener() {
                    @Override
                    public void onClickOpenChat() {
                        unlockLisenter.unlock();
                    }
                });
                openChatDialog.show();
            } else {
                showChatNeedRechargeDialog(activity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showChatNeedRechargeDialog(final Activity activity) {
        try {
            ToRechargeDialog toRechargeDialog = new ToRechargeDialog(activity);
            toRechargeDialog.setOnClickToRechargeListener(new ToRechargeDialog.OnClickToRechargeListener() {
                @Override
                public void onClickToRecharge() {
                    toRechargeActivity(activity);
                }
            });
            toRechargeDialog.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //视频聊天积分不足
    public void showVideoChatScoreNoEnough(final Activity activity) {
        try {
            VideoChatToRechargeDialog videoChatToRechargeDialog = new VideoChatToRechargeDialog(activity);
            videoChatToRechargeDialog.setOnClickToRechargeListener(new VideoChatToRechargeDialog.OnClickToRechargeListener() {
                @Override
                public void onClickToRecharge() {
                    activity.startActivity(new Intent(activity, RechargeActivity.class));
                    activity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
                }
            });
            videoChatToRechargeDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showVideoChatNoticeDialog(final Activity activity, final String avatar, final String nickName) {
        try {
            VideoChatNoticeDialog videoChatNoticeDialog = new VideoChatNoticeDialog(activity);
            videoChatNoticeDialog.setOnClickOpenChatListener(new VideoChatNoticeDialog.OnClickToVideoChatListener() {
                @Override
                public void onClickToVideoChat() {
                    Intent intent = new Intent(activity, VideoChatActivity.class);
                    intent.putExtra(VideoChatActivity.ARG_AVATAR, avatar);
                    intent.putExtra(VideoChatActivity.ARG_NICKNAME, nickName);
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
                }
            });
            videoChatNoticeDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
