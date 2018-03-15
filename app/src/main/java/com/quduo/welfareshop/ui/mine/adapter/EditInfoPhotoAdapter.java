package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

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
 * Time:2018/2/2 17:21
 * Description:编辑资料里面的图片展示
 */

public class EditInfoPhotoAdapter extends BaseAdapter {
    private Context context;
    private List<MyUserDetailInfo.PhotosBean> list;
    private LayoutInflater inflater;

    public EditInfoPhotoAdapter(Context context, List<MyUserDetailInfo.PhotosBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size() >= 9 ? list.size() : (list.size() + 1);
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

        PhotoViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_mine_edit_my_info_photo_item, parent, false);
            holder = new PhotoViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (PhotoViewHolder) convertView.getTag();
        }
        if (list.size() < 9) {
            if (position == 0) {
                holder.image.setImageResource(R.drawable.ic_add_photo);
                holder.delete.setVisibility(View.GONE);
            } else {
                holder.delete.setVisibility(View.VISIBLE);
                GlideApp.with(context)
                        .asBitmap()
                        .centerCrop()
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + list.get(position - 1).getUrl())
                        .into(holder.image);
            }
        } else {
            holder.delete.setVisibility(View.VISIBLE);
            GlideApp.with(context)
                    .asBitmap()
                    .centerCrop()
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + list.get(position).getUrl())
                    .into(holder.image);
        }


        return convertView;
    }


    static class PhotoViewHolder {
        @BindView(R.id.image)
        CustomHeightRoundedImageView image;
        @BindView(R.id.delete)
        ImageView delete;

        PhotoViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
