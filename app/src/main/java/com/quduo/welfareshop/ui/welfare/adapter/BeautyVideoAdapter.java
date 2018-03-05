package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.R;
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

public class BeautyVideoAdapter extends BaseQuickAdapter<VideoModelInfo, BeautyVideoAdapter.BeautyVideoViewHolder> {
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
    protected void convert(final BeautyVideoViewHolder holder, VideoModelInfo item) {
        BeautyVideoItemAdapter itemAdapter = new BeautyVideoItemAdapter(context, item.getList());
        holder.listView.setAdapter(itemAdapter);
        holder.title.setText(item.getTitle());
        itemAdapter.setOnItemClickItemVideoListener(new BeautyVideoItemAdapter.OnItemClickItemVideoListener() {
            @Override
            public void onItemClickItemVideo(int position1, int position2) {
                if (onItemClickVideoListener != null) {
                    onItemClickVideoListener.onItemClickVideo(holder.getLayoutPosition(), position1, position2);
                }
            }
        });
    }

    static class BeautyVideoViewHolder extends BaseViewHolder {
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.listView)
        CustomListView listView;

        BeautyVideoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickVideoListener {
        void onItemClickVideo(int position, int position1, int position2);
    }
}
