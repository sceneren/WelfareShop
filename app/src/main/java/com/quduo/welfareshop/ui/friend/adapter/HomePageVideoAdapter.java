package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.friend.entity.UserVideoInfo;
import com.quduo.welfareshop.widgets.RatioImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomePageVideoAdapter extends BaseAdapter {
    private Context context;
    private List<UserVideoInfo> list;
    private LayoutInflater inflater;

    public HomePageVideoAdapter(Context context, List<UserVideoInfo> list) {
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
        VideoViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_friend_others_home_page_video_item, parent, false);
            holder = new VideoViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (VideoViewHolder) convertView.getTag();
        }
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + list.get(position).getThumb())
                .centerCrop()
                .placeholder(R.drawable.ic_default_video)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
        if (list.size() > 3) {
            if (position == 2) {
                holder.more.setVisibility(View.VISIBLE);
            } else {
                holder.more.setVisibility(View.GONE);
            }
        } else {
            if (position == list.size() - 1) {
                holder.more.setVisibility(View.VISIBLE);
            } else {
                holder.more.setVisibility(View.GONE);
            }
        }
        return convertView;
    }

    static class VideoViewHolder {
        @BindView(R.id.image)
        RatioImageView image;
        @BindView(R.id.more)
        ImageView more;

        VideoViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
