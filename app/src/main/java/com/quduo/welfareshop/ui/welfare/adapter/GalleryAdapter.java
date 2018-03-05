package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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
public class GalleryAdapter extends BaseQuickAdapter<WelfareGalleryInfo, GalleryAdapter.GalleryViewHolder> {
    private Context context;

    public GalleryAdapter(Context context, List<WelfareGalleryInfo> list) {
        super(R.layout.fragment_welfare_grallery_item, list);
        this.context = context;
    }


    @Override
    protected void convert(GalleryViewHolder holder, WelfareGalleryInfo item) {
        int itemWidth = PtrLocalDisplay.SCREEN_WIDTH_PIXELS / 2 - SizeUtils.dp2px(5);
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        float scale = (float) itemWidth / (float) item.getPicWidth();
        params.width = itemWidth;
        params.height = (int) (scale * (float) item.getPicHeight());
        holder.imageView.setLayoutParams(params);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(item.getUrl())
                .into(holder.imageView);
        holder.name.setText(item.getTitle());
        if (holder.getLayoutPosition() % 2 == 0) {
            holder.tagBg.setColorFilter(Color.parseColor("#FEA0CA"));
        } else {
            holder.tagBg.setColorFilter(Color.parseColor("#ACD2FF"));
        }
    }

    class GalleryViewHolder extends BaseViewHolder {
        @BindView(R.id.imageView)
        SelectableRoundedImageView imageView;
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

}