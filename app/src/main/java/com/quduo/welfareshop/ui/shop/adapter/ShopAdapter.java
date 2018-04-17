package com.quduo.welfareshop.ui.shop.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.shop.entity.GoodsInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/27 14:13
 * Description:商城
 */

public class ShopAdapter extends BaseQuickAdapter<GoodsInfo, BaseViewHolder> {
    private Context context;

    public ShopAdapter(Context context, List<GoodsInfo> list) {
        super(R.layout.fragment_shop_item, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsInfo item) {
        ImageView image = helper.getView(R.id.image);
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb())
                .placeholder(R.drawable.ic_default_shop)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);
        helper.setText(R.id.name, item.getName());
        helper.setText(R.id.price, "￥" + item.getPrice());
        helper.setText(R.id.buy_number, item.getSales() + "人付款");
        Number num = Float.parseFloat(item.getPrice()) * 100;
        int giveNum = num.intValue() / 100;
        helper.setText(R.id.zeng, giveNum + "积分" );
    }

}
