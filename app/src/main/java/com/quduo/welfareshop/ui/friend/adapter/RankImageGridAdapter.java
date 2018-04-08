package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.widgets.RatioImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RankImageGridAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public RankImageGridAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list != null ? (list.size() > 3 ? 3 : list.size()) : 0;
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
        RankImageViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_friend_rank_item_item, parent, false);
            holder = new RankImageViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (RankImageViewHolder) convertView.getTag();
        }
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + list.get(position))
                .placeholder(R.drawable.ic_default_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
        return convertView;
    }

    static class RankImageViewHolder {
        @BindView(R.id.image)
        RatioImageView image;

        RankImageViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
