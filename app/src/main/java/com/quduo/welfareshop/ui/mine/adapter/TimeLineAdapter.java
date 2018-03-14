package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.github.vipulasri.timelineview.TimelineView;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.util.VectorDrawableUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/3/14 13:46
 * Description:快递信息时间轴
 */

public class TimeLineAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public TimeLineAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
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
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getCount());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TimeLineViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_mine_order_detail_logistics_item, parent, false);
            holder = new TimeLineViewHolder(convertView,getItemViewType(position));
            convertView.setTag(holder);
        } else {
            holder = (TimeLineViewHolder) convertView.getTag();
        }

        if (position == 0) {
            holder.timeMarker.setMarkerSize(SizeUtils.dp2px(15));
            holder.timeMarker.setMarker(VectorDrawableUtils.getDrawable(context, R.drawable.ic_timeline_begin));
            holder.time.setTextColor(ContextCompat.getColor(context, R.color.theme_color));
            holder.content.setTextColor(ContextCompat.getColor(context, R.color.theme_color));
        } else {
            holder.timeMarker.setMarkerSize(SizeUtils.dp2px(8));
            holder.timeMarker.setMarker(VectorDrawableUtils.getDrawable(context, R.drawable.ic_timeline_end));
            holder.time.setTextColor(ContextCompat.getColor(context, R.color.des_text_color));
            holder.content.setTextColor(ContextCompat.getColor(context, R.color.des_text_color));
        }
        return convertView;
    }

    static class TimeLineViewHolder {
        @BindView(R.id.time_marker)
        TimelineView timeMarker;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.time)
        TextView time;

        TimeLineViewHolder(View view,int viewType) {
            ButterKnife.bind(this, view);
            timeMarker.initLine(viewType);
        }
    }
}
