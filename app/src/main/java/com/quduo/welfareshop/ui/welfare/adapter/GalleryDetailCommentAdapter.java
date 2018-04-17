package com.quduo.welfareshop.ui.welfare.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.GalleryCommentInfo;

import java.util.List;

public class GalleryDetailCommentAdapter extends BaseQuickAdapter<GalleryCommentInfo, BaseViewHolder> {
    public GalleryDetailCommentAdapter(@Nullable List<GalleryCommentInfo> data) {
        super(R.layout.fragment_welfare_gallery_detail_comment_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GalleryCommentInfo item) {
        helper.setText(R.id.content, item.getContent());
        GlideApp.with(mContext)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getAvatar())
                .placeholder(R.drawable.ic_default_avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into((ImageView) helper.getView(R.id.avatar));
    }
}
