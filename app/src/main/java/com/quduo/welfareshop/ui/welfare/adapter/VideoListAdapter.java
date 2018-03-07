package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.VideoInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/5 17:11
 * Description:视频列表
 */

public class VideoListAdapter extends BaseQuickAdapter<VideoInfo, BaseViewHolder> {
    private Context context;

    public VideoListAdapter(Context context, @Nullable List<VideoInfo> data) {
        super(R.layout.fragment_welfare_beauty_video_item_heng, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoInfo item) {
        ImageView imageView = helper.getView(R.id.image);
        helper.setText(R.id.title, item.getName());
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(item.getThumb())
                .into(imageView);
    }
}
