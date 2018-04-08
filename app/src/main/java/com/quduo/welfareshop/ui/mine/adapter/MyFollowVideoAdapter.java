package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.mine.entity.MyFollowVideoInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 11:41
 * Description:我收藏的视频
 */

public class MyFollowVideoAdapter extends BaseQuickAdapter<MyFollowVideoInfo, BaseViewHolder> {
    private Context context;

    public MyFollowVideoAdapter(Context context, List<MyFollowVideoInfo> list) {
        super(R.layout.fragment_my_follow_video_item, list);
        this.context = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, MyFollowVideoInfo item) {
        helper.setText(R.id.title, item.getName());
        helper.setText(R.id.play_number, "播放:" + item.getPlay_times());
        helper.setText(R.id.follow_number, "收藏:" + item.getFavor_times());
        ImageView image = helper.getView(R.id.image);
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb())
                .placeholder(R.drawable.ic_default_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);
    }

}
