package com.quduo.welfareshop.ui.friend.adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.friend.entity.UserVideoInfo;
import com.quduo.welfareshop.widgets.RatioImageView;

import java.util.List;

public class UserAllVideoAdapter extends BaseQuickAdapter<UserVideoInfo, BaseViewHolder> {
    public UserAllVideoAdapter(@Nullable List<UserVideoInfo> data) {
        super(R.layout.fragment_user_all_video_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserVideoInfo item) {
        RatioImageView thumb = helper.getView(R.id.thumb);
        GlideApp.with(mContext)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_video)
                .into(thumb);
    }
}
