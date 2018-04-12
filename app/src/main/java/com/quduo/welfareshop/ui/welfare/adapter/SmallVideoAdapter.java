package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
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
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_video)
                .apply(RequestOptions.bitmapTransform(new CenterCrop()))
                .into(videoPlayer.thumbImageView);
        videoPlayer.setCurrentInfo(item.isPayed(), item.getId(), new View.OnClickListener() {
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
        ImageView imageTag = helper.getView(R.id.image_tag);
        //显示图片标签
        if (StringUtils.isTrimEmpty(item.getTags()) || item.getTags().equals("null")) {
            helper.setGone(R.id.text_tag, false);
            helper.setGone(R.id.image_tag, true);
            GlideApp.with(context)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getTag_img())
                    .into(imageTag);
        } else {
            helper.setGone(R.id.text_tag, true);
            helper.setGone(R.id.image_tag, false);
            helper.setText(R.id.text_tag, item.getTags());
        }

    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        VideoInfo item = mData.get(position);
        holder.setImageResource(R.id.btn_zan, item.isIs_good() ? R.drawable.ic_video_zan_s : R.drawable.ic_video_zan_d);
        holder.setImageResource(R.id.btn_follow, item.getFavor_id() != 0 ? R.drawable.ic_video_follow_s : R.drawable.ic_video_follow_d);
    }

    public interface OnClickPlayListener {
        void onClickPlay(int position);
    }
}
