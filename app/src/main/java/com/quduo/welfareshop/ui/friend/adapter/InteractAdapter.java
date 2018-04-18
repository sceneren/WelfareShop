package com.quduo.welfareshop.ui.friend.adapter;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

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
import com.quduo.welfareshop.ui.friend.entity.InteractInfo;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

public class InteractAdapter extends BaseQuickAdapter<InteractInfo, BaseViewHolder> {

    public InteractAdapter(@Nullable List<InteractInfo> data) {
        super(R.layout.fragment_friend_interact_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InteractInfo item) {
        GlideApp.with(mContext)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getAvatar())
                .circleCrop()
                .placeholder(R.drawable.ic_default_avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into((ImageView) helper.getView(R.id.avatar));
        helper.setText(R.id.nickname, item.getNickname());
        helper.setText(R.id.age, String.valueOf(item.getAge()));
        if (item.getSex() == 1) {
            helper.setText(R.id.video_chat_times, item.getVideo_times() + "人和他1v1视频过");
            helper.setImageResource(R.id.sex, R.drawable.ic_male_white);
        } else {
            helper.setText(R.id.video_chat_times, item.getVideo_times() + "人和她1v1视频过");
            helper.setImageResource(R.id.sex, R.drawable.ic_female_white);
        }
        helper.setText(R.id.content, item.getContent());

        ImageView thumb = helper.getView(R.id.thumb);
        ViewGroup.LayoutParams params = thumb.getLayoutParams();
        int size = (int) ((float) PtrLocalDisplay.SCREEN_WIDTH_PIXELS * 2f / 3f);
        params.height = size;
        params.width = size;
        thumb.setLayoutParams(params);
        MultiTransformation multi = new MultiTransformation(
                new CenterCrop(),
                new RoundedCornersTransformation(SizeUtils.dp2px(5), 0));
        GlideApp.with(mContext)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb())
                .apply(RequestOptions.bitmapTransform(multi))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(thumb);

        if (item.getGood_users() != null && item.getGood_users().size() > 0) {
            String zanStr = item.getGood_users().get(0).getNickname();
            if (zanStr.length() > 4) {
                zanStr = zanStr.substring(0, 4);
            }
            zanStr += "...等" + item.getGood() + "人觉得赞";
            helper.setText(R.id.zan_text, zanStr);

            if (item.getGood_users().size() == 1) {
                helper.setGone(R.id.zan_avatar_1, true);
                helper.setGone(R.id.zan_avatar_2, false);
                helper.setGone(R.id.zan_avatar_3, false);

                GlideApp.with(mContext)
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getGood_users().get(0).getAvatar())
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_default_avatar)
                        .into((ImageView) helper.getView(R.id.zan_avatar_1));

            } else if (item.getGood_users().size() == 2) {
                helper.setGone(R.id.zan_avatar_1, true);
                helper.setGone(R.id.zan_avatar_2, true);
                helper.setGone(R.id.zan_avatar_3, false);
                GlideApp.with(mContext)
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getGood_users().get(0).getAvatar())
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_default_avatar)
                        .into((ImageView) helper.getView(R.id.zan_avatar_1));
                GlideApp.with(mContext)
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getGood_users().get(1).getAvatar())
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_default_avatar)
                        .into((ImageView) helper.getView(R.id.zan_avatar_2));
            } else {
                helper.setGone(R.id.zan_avatar_1, true);
                helper.setGone(R.id.zan_avatar_2, true);
                helper.setGone(R.id.zan_avatar_3, true);
                GlideApp.with(mContext)
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getGood_users().get(0).getAvatar())
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_default_avatar)
                        .into((ImageView) helper.getView(R.id.zan_avatar_1));
                GlideApp.with(mContext)
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getGood_users().get(1).getAvatar())
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_default_avatar)
                        .into((ImageView) helper.getView(R.id.zan_avatar_2));
                GlideApp.with(mContext)
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getGood_users().get(2).getAvatar())
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_default_avatar)
                        .into((ImageView) helper.getView(R.id.zan_avatar_3));
            }

        } else {
            helper.setText(R.id.zan_text, "");
            helper.setGone(R.id.zan_avatar_1, false);
            helper.setGone(R.id.zan_avatar_2, false);
            helper.setGone(R.id.zan_avatar_3, false);
        }
        helper.setText(R.id.zan_times, String.valueOf(item.getGood()));
        helper.setImageResource(R.id.btn_zan, item.isIs_good() ? R.drawable.ic_friend_interact_zan_s : R.drawable.ic_friend_interact_zan_d);
        helper.addOnClickListener(R.id.thumb);
        helper.addOnClickListener(R.id.btn_zan);
    }
}
