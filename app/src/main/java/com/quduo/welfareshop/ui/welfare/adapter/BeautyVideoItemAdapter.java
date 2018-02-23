package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.ui.welfare.entity.VideoTypeInfo;
import com.quduo.welfareshop.widgets.CustomeGridView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/23 16:04
 * Description:美女视频
 */

public class BeautyVideoItemAdapter extends BaseAdapter {
    private Context context;
    private List<VideoTypeInfo> list;
    private LayoutInflater inflater;

    public BeautyVideoItemAdapter(Context context, List<VideoTypeInfo> list) {
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
        BeautyVideoItemViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_welfare_beauty_video_item_item, parent, false);
            holder = new BeautyVideoItemViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (BeautyVideoItemViewHolder) convertView.getTag();
        }
        VideoTypeInfo info = list.get(position);
        if (info.getType() == 0) {
            //大图
            holder.gridView.setNumColumns(1);
            BeautyVideoBigAdapter adapter = new BeautyVideoBigAdapter(context, info.getList());
            holder.gridView.setAdapter(adapter);
        } else if (info.getType() == 1) {
            //横图
            holder.gridView.setNumColumns(2);
            BeautyVideoHengAdapter adapter = new BeautyVideoHengAdapter(context, info.getList());
            holder.gridView.setAdapter(adapter);
        } else if (info.getType() == 2) {
            //2张竖图
            holder.gridView.setNumColumns(2);
            BeautyVideoShuAdapter adapter = new BeautyVideoShuAdapter(context, info.getList());
            holder.gridView.setAdapter(adapter);
        } else {
            //3张竖图
            holder.gridView.setNumColumns(3);
            BeautyVideoShu2Adapter adapter = new BeautyVideoShu2Adapter(context, info.getList());
            holder.gridView.setAdapter(adapter);
        }
        return convertView;
    }

    static class BeautyVideoItemViewHolder {
        @BindView(R.id.gridView)
        CustomeGridView gridView;

        BeautyVideoItemViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
