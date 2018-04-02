package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.view.ViewGroup;
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
import com.quduo.welfareshop.ui.friend.entity.OtherSimpleUserInfo;
import com.quduo.welfareshop.util.DistanceUtil;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/2/1 18:00
 * Description:附近的人
 */

public class NearAdapter extends BaseQuickAdapter<OtherSimpleUserInfo, BaseViewHolder> {
    private Context context;

    public NearAdapter(Context context, List<OtherSimpleUserInfo> list) {
        super(R.layout.fragment_friend_near_item, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, OtherSimpleUserInfo item) {
        helper.setImageResource(R.id.sex, item.getSex() == 1 ? R.drawable.ic_male : R.drawable.ic_female);
        helper.setText(R.id.age, item.getSex() + "岁");
        helper.setText(R.id.name, item.getNickname());
        helper.setText(R.id.distance, DistanceUtil.formatDistance(item.getDistance()));
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
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_image)
                .apply(RequestOptions.bitmapTransform(multi))
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getAvatar())
                .into(image);

        helper.setText(R.id.status_text, item.getIs_busy() == 1 ? "忙碌" : "在线");
        helper.setImageResource(R.id.status_view, item.getIs_busy() == 1 ? R.drawable.ic_friend_status_1 : R.drawable.ic_friend_status_0);
    }

}
