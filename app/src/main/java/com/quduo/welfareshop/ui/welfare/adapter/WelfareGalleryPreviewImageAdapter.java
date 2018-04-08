package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.PhotoView;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/22 12:33
 * Description:预览图片
 */

public class WelfareGalleryPreviewImageAdapter extends PagerAdapter {
    private Context context;
    private List<String> list;
    private boolean payed;
    private OnClickOpenVipListener onClickOpenVipListener;

    private boolean flag = false;

    public WelfareGalleryPreviewImageAdapter(Context context, List<String> list, boolean payed) {
        this.context = context;
        this.list = list;
        this.payed = payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
        flag = true;
        notifyDataSetChanged();
    }

    public void setOnClickOpenVipListener(OnClickOpenVipListener onClickOpenVipListener) {
        this.onClickOpenVipListener = onClickOpenVipListener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_preview_image_unlock, container, false);
        view.findViewById(R.id.open_vip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickOpenVipListener != null) {
                    onClickOpenVipListener.onClickOpenVip();
                }
            }
        });
        RelativeLayout noOpenLayout = view.findViewById(R.id.no_open_layout);
        PhotoView photoView = view.findViewById(R.id.photoView);
        GlideApp.with(context)
                .load(list.get(position))
                .placeholder(R.drawable.ic_default_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(photoView);
        noOpenLayout.setVisibility((!payed && position >= 8) ? View.VISIBLE : View.GONE);
        container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return view;

    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public interface OnClickOpenVipListener {
        void onClickOpenVip();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (flag) {
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}
