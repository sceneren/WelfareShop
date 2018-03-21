package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.VideoInfo;
import com.quduo.welfareshop.widgets.MyVideoPlayer;

import java.util.List;

import cn.jzvd.JZVideoPlayer;

/**
 * Author:scene
 * Time:2018/2/9 10:17
 * Description:小视频
 */

public class SmallVideoAdapter extends BaseQuickAdapter<VideoInfo, BaseViewHolder> {
    private Context context;
    private OnClickPlayListener onClickPlayListener;

    public SmallVideoAdapter(Context context, List<VideoInfo> list) {
        super(R.layout.fragment_welfare_small_video_item, list);
        this.context = context;
    }

    public void setOnClickPlayListener(OnClickPlayListener onClickPlayListener) {
        this.onClickPlayListener = onClickPlayListener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, VideoInfo item) {
        MyVideoPlayer videoPlayer = helper.getView(R.id.video_player);
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        videoPlayer.setUp(item.getUrl(), JZVideoPlayer.SCREEN_WINDOW_LIST, item.getName());
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb())
                .into(videoPlayer.thumbImageView);
        videoPlayer.setCurrentInfo(item.getScore(), item.isPayed(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickPlayListener != null) {
                    onClickPlayListener.onClickPlay(helper.getLayoutPosition());
                }
            }
        });
        helper.setText(R.id.play_number, "播放：" + item.getPlay_times());
        helper.setText(R.id.follow_number, "收藏：" + item.getFavor_times());
        helper.setText(R.id.zan_number, "点赞：" + item.getGood());
        helper.setImageResource(R.id.btn_zan, item.isIs_good() ? R.drawable.ic_video_zan_s : R.drawable.ic_video_zan_d);
        helper.setImageResource(R.id.btn_follow, item.getFavor_id() != 0 ? R.drawable.ic_video_follow_s : R.drawable.ic_video_follow_d);
        helper.addOnClickListener(R.id.btn_zan);
        helper.addOnClickListener(R.id.btn_follow);
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        if (payloads != null) {
            VideoInfo item = mData.get(position);
            holder.setImageResource(R.id.btn_zan, item.isIs_good() ? R.drawable.ic_video_zan_s : R.drawable.ic_video_zan_d);
            holder.setImageResource(R.id.btn_follow, item.getFavor_id() != 0 ? R.drawable.ic_video_follow_s : R.drawable.ic_video_follow_d);
        } else {
            onBindViewHolder(holder, position);
        }
    }

    public interface OnClickPlayListener {
        void onClickPlay(int position);
    }
}
