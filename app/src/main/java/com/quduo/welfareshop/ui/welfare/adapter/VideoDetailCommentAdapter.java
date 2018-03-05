package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/3/5 16:33
 * Description:视频详情评论
 */

public class VideoDetailCommentAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public VideoDetailCommentAdapter(Context context, List<String> list) {
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
        VideoDetailCommentViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_welfare_video_detail_comment_item, parent, false);
            holder = new VideoDetailCommentViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (VideoDetailCommentViewHolder) convertView.getTag();
        }

        return convertView;
    }

    static class VideoDetailCommentViewHolder {
        @BindView(R.id.avatar)
        SelectableRoundedImageView avatar;
        @BindView(R.id.nickname)
        TextView nickname;
        @BindView(R.id.content)
        TextView content;

        VideoDetailCommentViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
