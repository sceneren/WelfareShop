package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.content.pm.ActivityInfo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.VideoInfo;

import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Author:scene
 * Time:2018/2/9 10:17
 * Description:小视频
 */

public class SmallVideoAdapter extends BaseQuickAdapter<VideoInfo, BaseViewHolder> {
    private Context context;
    private List<VideoInfo> list;

    public SmallVideoAdapter(Context context, List<VideoInfo> list) {
        super(R.layout.fragment_welfare_small_video_item, list);
        this.context = context;
        this.list = list;
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoInfo item) {
        JZVideoPlayerStandard videoPlayer = helper.getView(R.id.video_player);
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        videoPlayer.setUp(item.getUrl(), JZVideoPlayer.SCREEN_WINDOW_LIST, item.getName());
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb())
                .into(videoPlayer.thumbImageView);
        helper.setText(R.id.play_number, "");
        helper.setText(R.id.play_number, "");
        helper.setText(R.id.play_number, "");
        helper.setText(R.id.play_number, "");
        helper.setText(R.id.play_number, "");
    }

}
