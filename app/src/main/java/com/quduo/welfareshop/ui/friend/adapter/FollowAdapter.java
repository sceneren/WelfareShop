package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.friend.entity.FollowUserInfo;
import com.quduo.welfareshop.util.DistanceUtil;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/2 11:47
 * Description:我的关注
 */
public class FollowAdapter extends BaseQuickAdapter<FollowUserInfo, BaseViewHolder> {
    private Context context;

    public FollowAdapter(Context context, List<FollowUserInfo> list) {
        super(R.layout.fragment_friend_follow_item, list);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, FollowUserInfo item) {
        SelectableRoundedImageView avatar = helper.getView(R.id.avatar);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getAvatar())
                .into(avatar);
        helper.setText(R.id.nickname, item.getNickname());
        helper.setImageResource(R.id.sex, item.getSex() == 1 ? R.drawable.ic_male_white : R.drawable.ic_female_white);
        helper.setText(R.id.age, String.valueOf(item.getAge()));
        helper.setText(R.id.distance, DistanceUtil.formatDistance(item.getDistance()));
        helper.setText(R.id.des, item.getSignature());
        helper.setBackgroundRes(R.id.layout_sex_age, item.getSex() == 1 ? R.drawable.bg_sex_age_male : R.drawable.bg_sex_age_female);
        helper.addOnClickListener(R.id.chat);
    }
}
