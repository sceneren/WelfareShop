package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * Author:scene
 * Time:2018/2/5 16:13
 * Description:照片墙
 */
public class OtherInfoImageAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public OtherInfoImageAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size() > 3 ? 3 : list.size();
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
        OtherInfoImageViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_other_info_image_item, parent, false);
            holder = new OtherInfoImageViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (OtherInfoImageViewHolder) convertView.getTag();
        }
        MultiTransformation multiTransformation = new MultiTransformation(
                new CenterCrop(), new RoundedCornersTransformation(SizeUtils.dp2px(5), 0)
        );
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_image)
                .apply(RequestOptions.bitmapTransform(multiTransformation))
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + list.get(position))
                .into(holder.image);
        return convertView;
    }

    static class OtherInfoImageViewHolder {
        @BindView(R.id.image)
        ImageView image;

        OtherInfoImageViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
