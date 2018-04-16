package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.NovelInfo;
import com.quduo.welfareshop.widgets.RatioImageView;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/24 11:53
 * Description:小爽文
 */

public class NovelIndexAdapter extends BaseQuickAdapter<NovelInfo, BaseViewHolder> {
    private Context context;

    public NovelIndexAdapter(Context context, @Nullable List<NovelInfo> data) {
        super(R.layout.fragment_welfare_novel_index_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NovelInfo item) {
        RatioImageView thumb = helper.getView(R.id.thumb);
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb_shu())
                .centerCrop()
                .placeholder(R.drawable.ic_default_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(thumb);
        helper.setText(R.id.title, item.getTitle());
        helper.setText(R.id.des, item.getDescription());
        helper.setText(R.id.read_number, "阅读:" + item.getView_times());
        helper.setText(R.id.follow_number, "收藏:" + item.getFavor_times());

    }
}
