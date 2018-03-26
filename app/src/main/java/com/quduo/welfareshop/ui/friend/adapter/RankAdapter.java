package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
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
import com.quduo.welfareshop.activity.PreviewImageActivity;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.friend.entity.OtherSimpleUserInfo;
import com.w4lle.library.NineGridlayout;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Author:scene
 * Time:2018/2/2 10:37
 * Description:人气榜
 */
public class RankAdapter extends BaseQuickAdapter<OtherSimpleUserInfo, BaseViewHolder> {
    private Context context;

    public RankAdapter(Context context, List<OtherSimpleUserInfo> list) {
        super(R.layout.fragment_friend_rank_item, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final OtherSimpleUserInfo item) {
        ImageView avatar = helper.getView(R.id.avatar);
        NineGridlayout imageLayout = helper.getView(R.id.image_layout);
        MultiTransformation multiTransformation = new MultiTransformation(
                new CenterCrop(), new RoundedCornersTransformation(SizeUtils.dp2px(5), 0)
        );
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .apply(RequestOptions.bitmapTransform(multiTransformation))
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getAvatar())
                .into(avatar);
        helper.setImageResource(R.id.sex, item.getSex() == 1 ? R.drawable.ic_male : R.drawable.ic_female);
        helper.setText(R.id.nickname, item.getNickname());
        helper.setText(R.id.age, item.getSex() + "岁");
        helper.setText(R.id.follow_number, item.getSubscribe() + "人关注");
        final ArrayList<String> images = new ArrayList<>();
        for (String str : item.getPhoto()) {
            images.add(MyApplication.getInstance().getConfigInfo().getFile_domain() + str);
        }

        NineGridImageAdapter imageAdapter = new NineGridImageAdapter(context, images);
        imageLayout.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, PreviewImageActivity.class);
                intent.putExtra(PreviewImageActivity.ARG_URLS, images);
                intent.putExtra(PreviewImageActivity.ARG_POSITION, position);
                context.startActivity(intent);
            }
        });
        imageLayout.setAdapter(imageAdapter);
        helper.setVisible(R.id.follow, item.getSubscribe_id() == 0);
        helper.addOnClickListener(R.id.follow);
    }

}
