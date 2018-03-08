package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.ui.welfare.entity.VideoModelInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/23 16:04
 * Description:美女视频
 */

public class BeautyVideoAdapter extends BaseQuickAdapter<VideoModelInfo, BaseViewHolder> {
    private Context context;

    private OnItemClickVideoListener onItemClickVideoListener;

    public BeautyVideoAdapter(Context context, List<VideoModelInfo> list) {
        super(R.layout.fragment_welfare_beauty_video_item, list);
        this.context = context;
    }

    public void setOnItemClickVideoListener(OnItemClickVideoListener onItemClickVideoListener) {
        this.onItemClickVideoListener = onItemClickVideoListener;
    }


    @Override
    protected void convert(final BaseViewHolder holder, VideoModelInfo item) {
        BeautyVideoItemAdapter itemAdapter = new BeautyVideoItemAdapter(context, item.getPositions());
        holder.setAdapter(R.id.listView, itemAdapter);
        holder.setText(R.id.title, item.getName());
        itemAdapter.setOnItemClickItemVideoListener(new BeautyVideoItemAdapter.OnItemClickItemVideoListener() {
            @Override
            public void onItemClickItemVideo(int position1, int position2) {
                if (onItemClickVideoListener != null) {
                    onItemClickVideoListener.onItemClickVideo(holder.getLayoutPosition(), position1, position2);
                }
            }
        });
    }

    public interface OnItemClickVideoListener {
        void onItemClickVideo(int position, int position1, int position2);
    }
}
