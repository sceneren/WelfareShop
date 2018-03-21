package com.quduo.welfareshop.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUserAction;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Author:scene
 * Time:2018/3/21 9:58
 * Description:重写播放器
 */

public class MyVideoPlayer extends JZVideoPlayerStandard {

    private int needScore = 0;
    private boolean payed = true;
    private OnClickListener onClickListener;

    public MyVideoPlayer(Context context) {
        super(context);
    }

    public MyVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCurrentInfo(int needScore, boolean payed, OnClickListener onClickListener) {
        this.needScore = needScore;
        this.payed = payed;
        this.onClickListener = onClickListener;
    }

    @Override
    public void init(Context context) {
        super.init(context);
        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (payed) {
                    Log.i(TAG, "onClick start [" + this.hashCode() + "] ");
                    if (dataSourceObjects == null || JZUtils.getCurrentFromDataSource(dataSourceObjects, currentUrlMapIndex) == null) {
                        Toast.makeText(getContext(), getResources().getString(cn.jzvd.R.string.no_url), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (currentState == CURRENT_STATE_NORMAL) {
                        if (!JZUtils.getCurrentFromDataSource(dataSourceObjects, currentUrlMapIndex).toString().startsWith("file") && !
                                JZUtils.getCurrentFromDataSource(dataSourceObjects, currentUrlMapIndex).toString().startsWith("/") &&
                                !JZUtils.isWifiConnected(getContext()) && !WIFI_TIP_DIALOG_SHOWED) {
                            showWifiDialog();
                            return;
                        }
                        startVideo();
                        onEvent(JZUserAction.ON_CLICK_START_ICON);
                    } else if (currentState == CURRENT_STATE_PLAYING) {
                        onEvent(JZUserAction.ON_CLICK_PAUSE);
                        Log.d(TAG, "pauseVideo [" + this.hashCode() + "] ");
                        JZMediaManager.pause();
                        onStatePause();
                    } else if (currentState == CURRENT_STATE_PAUSE) {
                        onEvent(JZUserAction.ON_CLICK_RESUME);
                        JZMediaManager.start();
                        onStatePlaying();
                    } else if (currentState == CURRENT_STATE_AUTO_COMPLETE) {
                        onEvent(JZUserAction.ON_CLICK_START_AUTO_COMPLETE);
                        startVideo();
                    }
                } else {
                    onClickListener.onClick(startButton);
                }
            }
        });
    }
}
