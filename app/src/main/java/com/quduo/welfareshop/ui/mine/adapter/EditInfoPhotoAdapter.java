package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
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
    private List<String> list;
    private LayoutInflater inflater;

    public EditInfoPhotoAdapter(Context context, List<String> list) {
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

        PhotoViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_mine_edit_my_info_photo_item, parent, false);
            holder = new PhotoViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (PhotoViewHolder) convertView.getTag();
        }
        if (list.size() < 9 && position == 0) {
            holder.image.setImageResource(R.drawable.ic_add_photo);
            holder.delete.setVisibility(View.GONE);
        } else {
            holder.delete.setVisibility(View.VISIBLE);
            String url = "http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg";
            GlideApp.with(context)
                    .asBitmap()
                    .centerCrop()
                    .load(url)
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
