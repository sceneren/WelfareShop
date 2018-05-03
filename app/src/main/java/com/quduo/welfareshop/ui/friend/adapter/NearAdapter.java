package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.StringUtils;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.friend.entity.NearInfo;
import com.quduo.welfareshop.util.DistanceUtil;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/2/1 18:00
 * Description:附近的人
 */

public class NearAdapter extends BaseQuickAdapter<NearInfo, BaseViewHolder> {
    private Context context;

    public NearAdapter(Context context, List<NearInfo> list) {
        super(R.layout.fragment_friend_new_near_item, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NearInfo item) {
        helper.setImageResource(R.id.sex, item.getSex() == 1 ? R.drawable.ic_near_sex_male_white : R.drawable.ic_near_sex_female_white);
        helper.setBackgroundRes(R.id.layout_sex_and_age, item.getSex() == 1 ? R.drawable.bg_sex_male : R.drawable.bg_sex_female);

        helper.setText(R.id.age, item.getAge() + "岁");
        helper.setText(R.id.name, item.getNickname());
        helper.setText(R.id.des, item.getSignature());
        helper.setGone(R.id.des, !StringUtils.isTrimEmpty(item.getSignature()));
        ImageView image = helper.getView(R.id.image);
        if (item.getAvatar_width() == 0) {
            item.setAvatar_width(1);
            item.setAvatar_height(1);
        } else {
            item.setAvatar_height(item.getAvatar_height());
            item.setAvatar_width(item.getAvatar_width());
        }

        int itemWidth = (int) ((PtrLocalDisplay.SCREEN_WIDTH_PIXELS - SizeUtils.dp2px(15)) / 2f);
        ViewGroup.LayoutParams params = image.getLayoutParams();
        float scale = (float) itemWidth / (float) item.getAvatar_width();
        params.width = itemWidth;
        params.height = (int) (scale * (float) item.getAvatar_height());
        image.setLayoutParams(params);

        MultiTransformation multi = new MultiTransformation(
                new CenterCrop(),
                new RoundedCornersTransformation(SizeUtils.dp2px(10), 0, RoundedCornersTransformation.CornerType.TOP));
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getAvatar())
                .placeholder(R.drawable.ic_default_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(multi))
                .into(image);

        helper.setText(R.id.status_text, item.getBusy() == 1 ? "忙碌" : "在线");
        helper.setImageResource(R.id.status_view, item.getBusy() == 1 ? R.drawable.ic_friend_status_1 : R.drawable.ic_friend_status_0);
        helper.setText(R.id.distance, DistanceUtil.formatDistance(item.getDistance()));
        helper.setText(R.id.total_video_chat_count, item.getVideo_times() + "人和TA视频过");

        //添加勋章
        LinearLayout medalLayout = helper.getView(R.id.medal_layout);
        medalLayout.removeAllViews();
        for (String medalUrl : item.getMedal()) {
            ImageView medal = new ImageView(mContext);
            ViewGroup.MarginLayoutParams layoutParams = new LinearLayout.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.width = SizeUtils.dp2px(20);
            layoutParams.height = SizeUtils.dp2px(20);
            layoutParams.setMarginEnd(SizeUtils.dp2px(5));
            medal.setLayoutParams(layoutParams);
            GlideApp.with(context)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + medalUrl)
                    .placeholder(R.drawable.ic_default_image)
                    .circleCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .apply(RequestOptions.bitmapTransform(multi))
                    .into(medal);
            medalLayout.addView(medal);
        }
        //添加视频过得人
        LinearLayout videoChatUserLayout = helper.getView(R.id.video_chat_user_layout);
        videoChatUserLayout.removeAllViews();
        for (int i = 0; i < item.getChat_user().size(); i++) {
            if (i < 3) {
                ImageView chatAvatar = new ImageView(mContext);
                ViewGroup.MarginLayoutParams layoutParams = new LinearLayout.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.width = SizeUtils.dp2px(20);
                layoutParams.height = SizeUtils.dp2px(20);
                layoutParams.setMarginEnd(SizeUtils.dp2px(5));
                chatAvatar.setLayoutParams(layoutParams);
                GlideApp.with(context)
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getChat_user().get(i).getAvatar())
                        .placeholder(R.drawable.ic_default_avatar)
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(chatAvatar);
                videoChatUserLayout.addView(chatAvatar);
            }
        }

    }

}
