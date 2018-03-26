package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.GalleryCateInfo;
import com.quduo.welfareshop.widgets.RatioImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/8 12:30
 * Description:美女图库的分类
 */

public class GalleryTypeGridAdapter extends BaseAdapter {
    private Context context;
    private List<GalleryCateInfo> list;
    private LayoutInflater inflater;

    public GalleryTypeGridAdapter(Context context, List<GalleryCateInfo> list) {
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
        TypeGridViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_welfare_gallery_type_grid_item, parent, false);
            holder = new TypeGridViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (TypeGridViewHolder) convertView.getTag();
        }
        GalleryCateInfo info=list.get(position);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain()+info.getThumb())
                .into(holder.image);
        holder.name.setText(info.getName());
        return convertView;
    }

    static class TypeGridViewHolder {
        @BindView(R.id.image)
        RatioImageView image;
        @BindView(R.id.name)
        TextView name;

        TypeGridViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
