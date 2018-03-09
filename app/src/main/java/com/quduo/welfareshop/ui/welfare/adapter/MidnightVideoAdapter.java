package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.VideoInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/24 10:37
 * Description:午夜影院
 */

public class MidnightVideoAdapter extends BaseQuickAdapter<VideoInfo, BaseViewHolder> {
    private Context context;

    public MidnightVideoAdapter(Context context, List<VideoInfo> list) {
        super(R.layout.fragment_welfare_midnight_video_item, list);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, VideoInfo item) {
        ImageView imageView = helper.getView(R.id.imageView);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb())
                .into(imageView);
        helper.setText(R.id.title, item.getName());
        helper.setText(R.id.play_number, "播放：" + item.getPlay_times());
        helper.setText(R.id.follow_number, "关注：" + item.getFavor_times());
        helper.setText(R.id.des, item.getDescription());
    }

    static class MidnightVideoViewHolder extends BaseViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.tag_bg)
        ImageView tagBg;
        @BindView(R.id.tag)
        TextView tag;
        @BindView(R.id.play_number)
        TextView playNumber;
        @BindView(R.id.follow_number)
        TextView followNumber;
        @BindView(R.id.des)
        TextView des;
        @BindView(R.id.video_time)
        TextView videoTime;

        MidnightVideoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
