package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.VideoInfo;
import com.quduo.welfareshop.widgets.RatioImageView;

import java.text.MessageFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/23 16:04
 * Description:美女视频-横图
 */

public class BeautyVideoHengAdapter extends BaseAdapter {
    private Context context;
    private List<VideoInfo> list;
    private LayoutInflater inflater;

    public BeautyVideoHengAdapter(Context context, List<VideoInfo> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BeautyVideoHengHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_welfare_beauty_video_item_heng, parent, false);
            holder = new BeautyVideoHengHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (BeautyVideoHengHolder) convertView.getTag();
        }
        VideoInfo info = list.get(position);
        holder.title.setText(info.getName());
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_video)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + list.get(position).getThumb())
                .into(holder.image);
        holder.playNumber.setText(MessageFormat.format("播放:{0}", info.getPlay_times()));
        holder.followNumber.setText(MessageFormat.format("收藏:{0}", info.getFavor_times()));
        return convertView;
    }

    static class BeautyVideoHengHolder {
        @BindView(R.id.image)
        RatioImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.play_number)
        TextView playNumber;
        @BindView(R.id.follow_number)
        TextView followNumber;

        BeautyVideoHengHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
