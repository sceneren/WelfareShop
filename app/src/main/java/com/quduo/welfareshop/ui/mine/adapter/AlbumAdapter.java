package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/19 9:54
 * Description:相册
 */

public class AlbumAdapter extends BaseQuickAdapter<MyUserDetailInfo.PhotosBean, BaseViewHolder> {
    public AlbumAdapter( @Nullable List<MyUserDetailInfo.PhotosBean> data) {
        super(R.layout.activity_album_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyUserDetailInfo.PhotosBean item) {
        ImageView imageView = helper.getView(R.id.imageView);
        GlideApp.with(mContext)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getUrl())
                .placeholder(R.drawable.ic_default_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView);
    }
}
