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
 * Description:美女视频-横图
 */

public class BeautyVideoShu2Adapter extends BaseAdapter {
    private Context context;
    private List<VideoInfo> list;
    private LayoutInflater inflater;

    public BeautyVideoShu2Adapter(Context context, List<VideoInfo> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return list.size();
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
        BeautyVideoShu2Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_welfare_beauty_video_item_shu_2, parent, false);
            holder = new BeautyVideoShu2Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (BeautyVideoShu2Holder) convertView.getTag();
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

    static class BeautyVideoShu2Holder {
        @BindView(R.id.image)
        CustomHeightRoundedImageView image;
        @BindView(R.id.title)
        TextView title;

        BeautyVideoShu2Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
