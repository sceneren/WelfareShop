package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

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
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.ui.friend.entity.OtherSimpleUserInfo;
import com.quduo.welfareshop.ui.friend.fragment.OtherInfoFragment;
import com.quduo.welfareshop.widgets.CustomGridView;

import org.greenrobot.eventbus.EventBus;

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
        CustomGridView imageGridView = helper.getView(R.id.imageGridView);
        MultiTransformation multiTransformation = new MultiTransformation(
                new CenterCrop(), new RoundedCornersTransformation(SizeUtils.dp2px(5), 0)
        );
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getAvatar())
                .placeholder(R.drawable.ic_default_avatar)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(multiTransformation))
                .into(avatar);
        helper.setImageResource(R.id.sex, item.getSex() == 1 ? R.drawable.ic_male : R.drawable.ic_female);
        helper.setText(R.id.nickname, item.getNickname());
        helper.setText(R.id.age, item.getSex() + "岁");
        helper.setText(R.id.follow_number, item.getSubscribe() + "人关注");
        helper.setText(R.id.des, item.getSignature());
        helper.setGone(R.id.des, !StringUtils.isTrimEmpty(item.getSignature()));

        RankImageGridAdapter gridAdapter = new RankImageGridAdapter(context, item.getPhoto());
        imageGridView.setAdapter(gridAdapter);
        imageGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventBus.getDefault().post(new StartBrotherEvent(OtherInfoFragment.newInstance(String.valueOf(item.getId()), false)));
            }
        });
    }

}
