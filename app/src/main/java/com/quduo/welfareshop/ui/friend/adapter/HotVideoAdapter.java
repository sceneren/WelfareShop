package com.quduo.welfareshop.ui.friend.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.friend.entity.HotVideoInfo;
import com.quduo.welfareshop.widgets.RatioImageView;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class HotVideoAdapter extends BaseQuickAdapter<HotVideoInfo, BaseViewHolder> {
    public HotVideoAdapter(@Nullable List<HotVideoInfo> data) {
        super(R.layout.fragment_friend_hot_video_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotVideoInfo item) {
        helper.setText(R.id.title, item.getContent());
        helper.setText(R.id.play_count, String.valueOf(item.getPlay_times()));
        helper.setText(R.id.nickname, item.getNickname());
        helper.setText(R.id.age, String.valueOf(item.getAge()));
        helper.setImageResource(R.id.sex, item.getSex() == 1 ? R.drawable.ic_male_white : R.drawable.ic_female_white);
        GlideApp.with(mContext)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getAvatar())
                .circleCrop()
                .placeholder(R.drawable.ic_default_avatar)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into((ImageView) helper.getView(R.id.avatar));
        MultiTransformation multi = new MultiTransformation(
                new CenterCrop(),
                new RoundedCornersTransformation(SizeUtils.dp2px(10), 0, RoundedCornersTransformation.CornerType.TOP));
        GlideApp.with(mContext)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb())
                .placeholder(R.drawable.ic_default_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(multi))
                .into((RatioImageView) helper.getView(R.id.image));
        helper.addOnClickListener(R.id.layout_to_video_detail);
        helper.addOnClickListener(R.id.layout_to_user_detail);
    }
}
