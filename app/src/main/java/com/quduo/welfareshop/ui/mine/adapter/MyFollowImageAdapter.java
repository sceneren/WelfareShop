package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.SizeUtils;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.WelfareGalleryInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/3/1 11:41
 * Description:我收藏的图片
 */

public class MyFollowImageAdapter extends RecyclerView.Adapter<MyFollowImageAdapter.MyFollowImageViewHolder> {
    private Context context;
    private List<WelfareGalleryInfo> list;

    public MyFollowImageAdapter(Context context, List<WelfareGalleryInfo> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public MyFollowImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyFollowImageViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_my_follow_image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyFollowImageViewHolder holder, int position) {
        WelfareGalleryInfo info = list.get(position);
        int itemWidth = PtrLocalDisplay.SCREEN_WIDTH_PIXELS / 2 - SizeUtils.dp2px(5);
        ViewGroup.LayoutParams params = holder.image.getLayoutParams();
        float scale = (float) itemWidth / (float) list.get(position).getPicWidth();
        params.width = itemWidth;
        params.height = (int) (scale * (float) list.get(position).getPicHeight());
        holder.image.setLayoutParams(params);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(info.getUrl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyFollowImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;

        MyFollowImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
