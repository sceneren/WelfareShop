package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.VideoInfo;
import com.quduo.welfareshop.widgets.RatioImageView;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class SelfieVideoAdapter extends BaseQuickAdapter<VideoInfo, BaseViewHolder> {
    private Context context;

    public SelfieVideoAdapter(Context context, @Nullable List<VideoInfo> data) {
        super(R.layout.fragment_welfare_selfie_video_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoInfo item) {
        helper.setText(R.id.title, item.getName());
        helper.setText(R.id.play_count, String.valueOf(item.getPlay_times()));
        RatioImageView imageView = helper.getView(R.id.image);
        MultiTransformation multi = new MultiTransformation(
                new CenterCrop(),
                new RoundedCornersTransformation(SizeUtils.dp2px(10), 0, RoundedCornersTransformation.CornerType.TOP));
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb())
                .placeholder(R.drawable.ic_default_video)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(multi))
                .into(imageView);
    }
}
