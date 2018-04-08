package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.w4lle.library.NineGridAdapter;

import java.util.List;


/**
 * Author:scene
 * Time:2018/2/2 11:25
 * Description:自定义的显示9宫格的adapter
 */
public class NineGridImageAdapter extends NineGridAdapter {

    public NineGridImageAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public int getCount() {
        return (list == null) ? 0 : list.size();
    }

    @Override
    public String getUrl(int position) {
        return getItem(position) == null ? null : (String) getItem(position);
    }

    @Override
    public Object getItem(int position) {
        return (list == null) ? null : list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view) {
        ImageView iv;
        if (view != null && view instanceof ImageView) {
            iv = (ImageView) view;
        } else {
            iv = new ImageView(context);
        }
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        GlideApp.with(context)
                .load(list.get(i))
                .placeholder(R.drawable.ic_default_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv);
        return iv;
    }

}
