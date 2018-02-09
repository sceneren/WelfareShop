package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.WelfareVideoInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Author:scene
 * Time:2018/2/9 10:17
 * Description:小视频
 */

public class SmallVideoAdapter extends RecyclerView.Adapter<SmallVideoAdapter.SmallVideoViewHolder> {
    private Context context;
    private List<WelfareVideoInfo> list;

    public SmallVideoAdapter(Context context, List<WelfareVideoInfo> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public SmallVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SmallVideoViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_welfare_small_video_item, parent, false));
    }

    @Override
    public void onBindViewHolder(SmallVideoViewHolder holder, int position) {
        WelfareVideoInfo info = list.get(position);
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        holder.videoPlayer.setUp(info.getVideoPath(), JZVideoPlayer.SCREEN_WINDOW_LIST, info.getTitle());
        GlideApp.with(context).load(info.getImagePath()).into(holder.videoPlayer.thumbImageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class SmallVideoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.video_player)
        JZVideoPlayerStandard videoPlayer;
        @BindView(R.id.play_number)
        TextView playNumber;
        @BindView(R.id.follow_number)
        TextView followNumber;
        @BindView(R.id.zan_number)
        TextView zanNumber;
        @BindView(R.id.btn_zan)
        ImageView btnZan;
        @BindView(R.id.btn_follow)
        ImageView btnFollow;

        SmallVideoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
