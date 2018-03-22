package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;
import com.quduo.welfareshop.widgets.CustomHeightRoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Author:scene
 * Time:2018/2/5 16:13
 * Description:照片墙
 */
public class MyInfoImageAdapter extends BaseAdapter {
    private Context context;
    private List<MyUserDetailInfo.PhotosBean> list;
    private LayoutInflater inflater;

    public MyInfoImageAdapter(Context context, List<MyUserDetailInfo.PhotosBean> list) {
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
        MyInfoImageViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_other_info_image_item, parent, false);
            holder = new MyInfoImageViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyInfoImageViewHolder) convertView.getTag();
        }

        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + list.get(position).getUrl())
                .into(holder.image);
        return convertView;
    }

    static class MyInfoImageViewHolder {
        @BindView(R.id.image)
        CustomHeightRoundedImageView image;

        MyInfoImageViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
