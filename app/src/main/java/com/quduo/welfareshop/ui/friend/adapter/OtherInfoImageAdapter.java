package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.widgets.CustomHeightRoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


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
        OtherInfoImageViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_other_info_image_item, parent, false);
            holder = new OtherInfoImageViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (OtherInfoImageViewHolder) convertView.getTag();
        }

        String url = "http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg";
        GlideApp.with(context)
                .asBitmap()
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_agreement)
                .into(holder.image);
        return convertView;
    }

    static class OtherInfoImageViewHolder {
        @BindView(R.id.image)
        CustomHeightRoundedImageView image;

        OtherInfoImageViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
