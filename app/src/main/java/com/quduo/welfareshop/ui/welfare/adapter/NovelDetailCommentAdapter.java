package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.NovelCommentInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NovelDetailCommentAdapter extends BaseAdapter {
    private Context context;
    private List<NovelCommentInfo> list;
    private LayoutInflater inflater;

    public NovelDetailCommentAdapter(Context context, List<NovelCommentInfo> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NovelDetailCommentViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_welfare_novel_detail_header_item, parent, false);
            holder = new NovelDetailCommentViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (NovelDetailCommentViewHolder) convertView.getTag();
        }
        NovelCommentInfo info = list.get(position);
        holder.content.setText(info.getContent());
        holder.name.setText(info.getNickname());
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + info.getAvatar())
                .placeholder(R.drawable.ic_default_avatar)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(holder.avatar);
        return convertView;
    }

    static class NovelDetailCommentViewHolder {
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.content)
        TextView content;

        NovelDetailCommentViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
