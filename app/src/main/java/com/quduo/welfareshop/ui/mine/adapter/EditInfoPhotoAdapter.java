package com.quduo.welfareshop.ui.mine.adapter;

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
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;
import com.quduo.welfareshop.widgets.RatioImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

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
        return list != null ? (list.size() > 3 ? 3 : list.size()) : 0;
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
        MultiTransformation multiTransformation=new MultiTransformation(
                new CenterCrop(),new RoundedCornersTransformation(SizeUtils.dp2px(5),0)
        );
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + list.get(position).getUrl())
                .placeholder(R.drawable.ic_default_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(multiTransformation))
                .into(holder.image);
        return convertView;
    }


    static class PhotoViewHolder {
        @BindView(R.id.image)
        RatioImageView image;

        PhotoViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
