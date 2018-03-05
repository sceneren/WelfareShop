package com.quduo.welfareshop.ui.friend.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.friend.model.VideoChatPresenter;
import com.quduo.welfareshop.ui.friend.view.IVideoChatView;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;

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
    @BindView(R.id.background_image)
    ImageView backgroundImage;
    @BindView(R.id.avatar)
    SelectableRoundedImageView avatar;
    @BindView(R.id.connection_image)
    ImageView connectionImage;
    @BindView(R.id.cancel_video)
    TextView cancelVideo;
    @BindView(R.id.open_cemare)
    TextView openCemare;
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_chat);
        unbinder = ButterKnife.bind(this);
        initView();
    }

    private void initView() {

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
