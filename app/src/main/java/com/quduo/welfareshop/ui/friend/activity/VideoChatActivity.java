package com.quduo.welfareshop.ui.friend.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.friend.model.VideoChatPresenter;
import com.quduo.welfareshop.ui.friend.view.IVideoChatView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/3/5 10:53
 * Description:视频聊天
 */

public class VideoChatActivity extends BaseMvpActivity<IVideoChatView, VideoChatPresenter> implements IVideoChatView {
    public static final String ARG_NICKNAME = "nickname";
    public static final String ARG_AVATAR = "avatar";

    @BindView(R.id.background_image)
    ImageView backgroundImage;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.connection_image)
    ImageView connectionImage;
    @BindView(R.id.cancel_video)
    TextView cancelVideo;
    @BindView(R.id.open_cemare)
    TextView openCemare;
    Unbinder unbinder;
    @BindView(R.id.nickname)
    TextView nickname;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_chat);
        unbinder = ButterKnife.bind(this);
        initView();
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_FRIEND_VIDEO_CHAT,0);
    }

    private void initView() {
        Intent intent = getIntent();
        String strNickName = intent.getStringExtra(ARG_NICKNAME);
        String strAvatar = intent.getStringExtra(ARG_AVATAR);
        GlideApp.with(this)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_image)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + strAvatar)
                .into(backgroundImage);
        GlideApp.with(this)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + strAvatar)
                .into(avatar);
        nickname.setText(strNickName);

        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                showDialog();
                cancel();
            }
        }.start();

    }

    void showDialog() {
        Dialog dialog = StyledDialog.buildIosAlert("提示", "暂时无人接听，请稍后再试", new MyDialogListener() {
            @Override
            public void onFirst() {
                onBackPressed();
            }

            @Override
            public void onSecond() {

            }
        }).setBtnText("确定").setActivity(VideoChatActivity.this).show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                onBackPressed();
            }
        });
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
    public VideoChatPresenter initPresenter() {
        return new VideoChatPresenter(this);
    }

    @Override
    protected void onDestroy() {
        try {
            countDownTimer.cancel();
            connectionImage.setImageResource(R.drawable.anim_video_connection);
            AnimationDrawable animationDrawable = (AnimationDrawable) connectionImage.getDrawable();
            animationDrawable.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.cancel_video)
    public void onClickCancelVideo() {
        try {
            onBackPressedSupport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.open_cemare)
    public void onClickOpenCemare() {
        try {
            if (openCemare.getText().toString().equals("打开摄像头")) {
                openCemare.setText("关闭摄像头");
                openCemare.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(VideoChatActivity.this, R.drawable.ic_open_cemare), null, null);
            } else {
                openCemare.setText("打开摄像头");
                openCemare.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(VideoChatActivity.this, R.drawable.ic_close_cemare), null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        try {
            connectionImage.setImageResource(R.drawable.anim_video_connection);
            AnimationDrawable animationDrawable = (AnimationDrawable) connectionImage.getDrawable();
            animationDrawable.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
