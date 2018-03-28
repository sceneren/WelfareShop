package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.mine.entity.MyFollowNovelInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 11:41
 * Description:我收藏的小说
 */

public class MyFollowNovelAdapter extends BaseQuickAdapter<MyFollowNovelInfo, BaseViewHolder> {
    private Context context;

    public MyFollowNovelAdapter(Context context, List<MyFollowNovelInfo> list) {
        super(R.layout.fragment_my_follow_novel_item, list);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, MyFollowNovelInfo item) {
        helper.setText(R.id.title, item.getTitle());
        ImageView imageView = helper.getView(R.id.image);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_image)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb_shu())
                .into(imageView);
    }

}
