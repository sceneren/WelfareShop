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
import com.quduo.welfareshop.ui.welfare.entity.VideoCommentInfo;
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
    private List<VideoCommentInfo> list;
    private LayoutInflater inflater;

    public VideoDetailCommentAdapter(Context context, List<VideoCommentInfo> list) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        VideoDetailCommentViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_welfare_video_detail_comment_item, parent, false);
            holder = new VideoDetailCommentViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (VideoDetailCommentViewHolder) convertView.getTag();
        }
        VideoCommentInfo info = list.get(position);
        holder.nickname.setText(info.getNickname());
        holder.content.setText(info.getContent());
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + info.getAvatar())
                .into(holder.avatar);
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
