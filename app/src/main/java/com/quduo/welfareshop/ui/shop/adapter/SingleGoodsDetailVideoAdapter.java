package com.quduo.welfareshop.ui.shop.adapter;

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
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.ui.shop.entity.SingleGoodsDetailVideoInfo;
import com.quduo.welfareshop.widgets.RatioImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingleGoodsDetailVideoAdapter extends BaseAdapter {
    private Context context;
    private List<SingleGoodsDetailVideoInfo> list;
    private LayoutInflater inflater;

    public SingleGoodsDetailVideoAdapter(Context context, List<SingleGoodsDetailVideoInfo> list) {
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
            convertView = inflater.inflate(R.layout.activity_single_goods_detail_item, parent, false);
            holder = new VideoViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (VideoViewHolder) convertView.getTag();
        }
        SingleGoodsDetailVideoInfo info = list.get(position);
        holder.name.setText(info.getTitle());
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + info.getThumb())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumb);
        return convertView;
    }

    static class VideoViewHolder {
        @BindView(R.id.thumb)
        RatioImageView thumb;
        @BindView(R.id.name)
        TextView name;

        VideoViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
