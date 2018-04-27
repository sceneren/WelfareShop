package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.ui.welfare.entity.VideoTypeInfo;
import com.quduo.welfareshop.widgets.CustomGridView;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/23 16:04
 * Description:美女视频
 */

public class MidNightNewAdapter extends BaseQuickAdapter<VideoTypeInfo, BaseViewHolder> {
    private Context context;
    private OnItemClickItemVideoListener onItemClickItemVideoListener;

    public MidNightNewAdapter(Context context, List<VideoTypeInfo> list) {
        super(R.layout.fragment_welfare_beauty_video_item_item, list);
        this.context = context;
    }

    public void setOnItemClickItemVideoListener(OnItemClickItemVideoListener onItemClickItemVideoListener) {
        this.onItemClickItemVideoListener = onItemClickItemVideoListener;
    }

    @Override
    protected void convert(final BaseViewHolder holder, VideoTypeInfo item) {
        CustomGridView gridView = holder.getView(R.id.gridView);


        if (item.getType() == 4) {
            //大图
            gridView.setNumColumns(1);
            BeautyVideoBigAdapter adapter = new BeautyVideoBigAdapter(context, item.getVideos());
            gridView.setAdapter(adapter);
        } else if (item.getType() == 1) {
            //横图
            gridView.setNumColumns(2);
            BeautyVideoHengAdapter adapter = new BeautyVideoHengAdapter(context, item.getVideos());
            gridView.setAdapter(adapter);
        } else if (item.getType() == 2) {
            //2张竖图
            gridView.setNumColumns(2);
            BeautyVideoShuAdapter adapter = new BeautyVideoShuAdapter(context, item.getVideos());
            gridView.setAdapter(adapter);
        } else {
            //3张竖图
            gridView.setNumColumns(3);
            BeautyVideoShu2Adapter adapter = new BeautyVideoShu2Adapter(context, item.getVideos());
            gridView.setAdapter(adapter);
        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int childPosition, long id) {
                if (onItemClickItemVideoListener != null) {
                    onItemClickItemVideoListener.onItemClickItemVideo(holder.getLayoutPosition()-1, childPosition);
                }
            }
        });
    }

    public interface OnItemClickItemVideoListener {
        void onItemClickItemVideo(int position, int childPosition);
    }
}
