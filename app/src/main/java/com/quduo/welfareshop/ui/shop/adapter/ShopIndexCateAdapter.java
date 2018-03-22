package com.quduo.welfareshop.ui.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.shop.entity.ShopCateInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/3/22 14:24
 * Description:商品分类
 */

public class ShopIndexCateAdapter extends BaseAdapter {
    private Context context;
    private List<ShopCateInfo> list;
    private LayoutInflater inflater;

    public ShopIndexCateAdapter(Context context, List<ShopCateInfo> list) {
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
        ShopIndexCateViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_shop_header_item, parent, false);
            holder = new ShopIndexCateViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ShopIndexCateViewHolder) convertView.getTag();
        }
        ShopCateInfo info = list.get(position);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_shop)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + info.getThumb())
                .into(holder.image);
        holder.name.setText(info.getName());
        return convertView;
    }

    static class ShopIndexCateViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;

        ShopIndexCateViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
