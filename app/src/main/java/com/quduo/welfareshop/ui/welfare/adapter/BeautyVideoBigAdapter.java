package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.VideoInfo;
import com.quduo.welfareshop.widgets.CustomHeightRoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/23 16:04
 * Description:美女视频
 */

public class BeautyVideoBigAdapter extends BaseAdapter {

    private Context context;
    private List<VideoInfo> list;
    private LayoutInflater inflater;

    public BeautyVideoBigAdapter(Context context, List<VideoInfo> list) {
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
        BeautyVideoBigHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_welfare_beauty_video_item_big, parent, false);
            holder = new BeautyVideoBigHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (BeautyVideoBigHolder) convertView.getTag();
        }
        VideoInfo info = list.get(position);
        holder.title.setText(info.getName());
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain()+list.get(position).getThumb())
                .into(holder.image);
        return convertView;
    }

    static class BeautyVideoBigHolder {
        @BindView(R.id.image)
        CustomHeightRoundedImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.play_number)
        TextView playNumber;
        @BindView(R.id.follow_number)
        TextView followNumber;

        BeautyVideoBigHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
