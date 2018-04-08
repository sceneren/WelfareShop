package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.WelfareGalleryInfo;

import java.util.List;

import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/2/8 11:39
 * Description:福利图库
 */
public class GalleryAdapter extends BaseQuickAdapter<WelfareGalleryInfo, BaseViewHolder> {
    private Context context;

    public GalleryAdapter(Context context, List<WelfareGalleryInfo> list) {
        super(R.layout.fragment_welfare_gallery_item, list);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder holder, WelfareGalleryInfo item) {
        ImageView avatar = holder.getView(R.id.avatar);
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
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb())
                .placeholder(R.drawable.ic_default_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image1);

        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getAvatar())
                .placeholder(R.drawable.ic_default_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(avatar);
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb_small().get(0))
                .placeholder(R.drawable.ic_default_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image2);
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb_small().get(1))
                .placeholder(R.drawable.ic_default_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image3);
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb_small().get(2))
                .placeholder(R.drawable.ic_default_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image4);

        holder.setText(R.id.title, item.getName());
        holder.setText(R.id.view_number, String.valueOf(item.getView_times()));
        holder.setText(R.id.follow_number, String.valueOf(item.getFavor_times()));
        holder.setText(R.id.zan_number, String.valueOf(item.getGood()));
        holder.setText(R.id.follow_number, String.valueOf(item.getFavor_times()));
        holder.setText(R.id.zan_number, String.valueOf(item.getGood()));
        if (item.getFavor_id() != 0) {
            holder.setImageResource(R.id.btn_follow, R.drawable.ic_gallery_follow_s);
        } else {
            holder.setImageResource(R.id.btn_follow, R.drawable.ic_gallery_follow_d);
        }
        if (item.isIs_good()) {
            holder.setImageResource(R.id.btn_zan, R.drawable.ic_video_zan_s);
        } else {
            holder.setImageResource(R.id.btn_zan, R.drawable.ic_video_zan_d);
        }
        holder.addOnClickListener(R.id.layout_zan);
        holder.addOnClickListener(R.id.layout_follow);
    }

}