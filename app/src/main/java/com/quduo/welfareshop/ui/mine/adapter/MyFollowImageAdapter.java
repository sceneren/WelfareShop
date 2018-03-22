package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.mine.entity.MyFollowGalleryInfo;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;

import java.util.List;

import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/2/8 11:39
 * Description:我收藏的图库
 */
public class MyFollowImageAdapter extends BaseQuickAdapter<MyFollowGalleryInfo, BaseViewHolder> {
    private Context context;

    public MyFollowImageAdapter(Context context, List<MyFollowGalleryInfo> list) {
        super(R.layout.fragment_welfare_gallery_item, list);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder holder, MyFollowGalleryInfo item) {
        SelectableRoundedImageView avatar = holder.getView(R.id.avatar);
        ImageView image1 = holder.getView(R.id.image1);
        ImageView image2 = holder.getView(R.id.image2);
        ImageView image3 = holder.getView(R.id.image3);
        ImageView image4 = holder.getView(R.id.image4);

        int baseWidth = PtrLocalDisplay.SCREEN_WIDTH_PIXELS - SizeUtils.dp2px(15);
        float base = baseWidth / 1048f;
        ViewGroup.LayoutParams smallParams = image2.getLayoutParams();
        int smallSize = (int) (base * 372);
        smallParams.width = smallSize;
        smallParams.height = smallSize;
        image2.setLayoutParams(smallParams);
        image3.setLayoutParams(smallParams);
        image4.setLayoutParams(smallParams);

        ViewGroup.LayoutParams bigParams = image1.getLayoutParams();
        int bigWidth = (int) (base * 676f);
        int bigHeight = smallSize * 3 + SizeUtils.dp2px(10);
        bigParams.width = bigWidth;
        bigParams.height = bigHeight;
        image1.setLayoutParams(bigParams);

        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb())
                .into(image1);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getAvatar())
                .into(avatar);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb_small().get(0))
                .into(image2);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb_small().get(1))
                .into(image3);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb_small().get(2))
                .into(image4);

        holder.setText(R.id.title, item.getName());
        holder.setText(R.id.follow_number, String.valueOf(item.getFavor_times()));
        holder.setImageResource(R.id.follow, R.drawable.ic_gallery_follow_s);
        holder.addOnClickListener(R.id.layout_follow);
    }

}