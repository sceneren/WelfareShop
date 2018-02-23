package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.ui.welfare.entity.VideoInfo;
import com.quduo.welfareshop.ui.welfare.entity.VideoModelInfo;
import com.quduo.welfareshop.widgets.CustomListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/23 16:04
 * Description:美女视频
 */

public class BeautyVideoAdapter extends RecyclerView.Adapter<BeautyVideoAdapter.BeautyVideoViewHolder> {
    private Context context;
    private List<VideoModelInfo> list;

    public BeautyVideoAdapter(Context context, List<VideoModelInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public BeautyVideoAdapter.BeautyVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BeautyVideoViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_welfare_beauty_video_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BeautyVideoAdapter.BeautyVideoViewHolder holder, int position) {
        BeautyVideoItemAdapter itemAdapter=new BeautyVideoItemAdapter(context,list.get(position).getList());
        holder.listView.setAdapter(itemAdapter);
        holder.title.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class BeautyVideoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.listView)
        CustomListView listView;

        BeautyVideoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
