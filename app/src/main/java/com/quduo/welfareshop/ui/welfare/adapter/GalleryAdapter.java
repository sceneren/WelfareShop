package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.WelfareGalleryInfo;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/2/8 11:39
 * Description:福利图库
 */
public class GalleryAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<WelfareGalleryInfo> list;

    public GalleryAdapter(Context context, List<WelfareGalleryInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GalleryViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_welfare_grallery_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        WelfareGalleryInfo info = list.get(position);
        GalleryViewHolder viewHolder = (GalleryViewHolder) holder;
        int itemWidth = PtrLocalDisplay.SCREEN_WIDTH_PIXELS / 2 - SizeUtils.dp2px(5);
        ViewGroup.LayoutParams params = viewHolder.image.getLayoutParams();
        float scale = (float) itemWidth / (float) list.get(position).getPicWidth();
        params.width = itemWidth;
        params.height = (int) (scale * (float) list.get(position).getPicHeight());
        viewHolder.image.setLayoutParams(params);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(list.get(position).getUrl())
                //.override(width, height)
                .into(viewHolder.image);
        //loadImage(list.get(position).getUrl(), viewHolder.image);

        if (position % 2 == 0) {
            viewHolder.tagBg.setColorFilter(Color.parseColor("#FEA0CA"));
        } else {
            viewHolder.tagBg.setColorFilter(Color.parseColor("#ACD2FF"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class GalleryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        SelectableRoundedImageView image;
        @BindView(R.id.tag)
        TextView tag;
        @BindView(R.id.tag_bg)
        ImageView tagBg;
        @BindView(R.id.count)
        TextView count;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.click_num)
        TextView clickNum;
        @BindView(R.id.follow_num)
        TextView followNum;

        GalleryViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    private void loadImage(String url, final ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        int imageWidth = resource.getIntrinsicWidth();
                        int imageHeight = resource.getIntrinsicHeight();
                        int imageViewWidth = imageView.getWidth();
                        double scale = imageWidth * 1.0 / imageViewWidth;
                        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                        layoutParams.height = (int) (imageHeight / scale);
                        imageView.setLayoutParams(layoutParams);

                        imageView.setImageDrawable(resource);
                    }
                });
    }
}