package com.quduo.welfareshop.ui.shop.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;

import java.util.List;

import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/2/27 14:13
 * Description:商城
 */

public class ShopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context context;

    public ShopAdapter(Context context, List<String> list) {
        super(R.layout.fragment_shop_item, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView image = helper.getView(R.id.image);
        String url = "http://pic19.nipic.com/20120214/3145425_134109747000_2.jpg";
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_shop)
                .load(url)
                .into(image);
    }

}
