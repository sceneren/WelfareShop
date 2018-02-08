package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.widgets.CustomHeightRoundedImageView;

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
    private List<String> list;
    private LayoutInflater inflater;

    public GalleryTypeGridAdapter(Context context, List<String> list) {
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
        String url = "http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg";
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(url)
                .into(holder.image);
        holder.name.setText(list.get(position));
        return convertView;
    }

    static class TypeGridViewHolder {
        @BindView(R.id.image)
        CustomHeightRoundedImageView image;
        @BindView(R.id.name)
        TextView name;

        TypeGridViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
