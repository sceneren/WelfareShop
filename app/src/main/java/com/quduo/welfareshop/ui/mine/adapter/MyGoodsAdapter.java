package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.mine.entity.MyFollowGoodsInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/3/1 10:55
 * Description:收藏的商品
 */

public class MyGoodsAdapter extends BaseQuickAdapter<MyFollowGoodsInfo, BaseViewHolder> {
    private Context context;

    public MyGoodsAdapter(Context context, List<MyFollowGoodsInfo> list) {
        super(R.layout.fragment_my_goods_item, list);
        this.context = context;

    }

    @Override
    protected void convert(BaseViewHolder helper, MyFollowGoodsInfo item) {
        ImageView goodsImage = helper.getView(R.id.goods_image);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_shop)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb())
                .into(goodsImage);
        helper.setText(R.id.goods_name, item.getName());
        helper.setText(R.id.goods_price, "￥" + item.getPrice());
        helper.setText(R.id.sales, item.getSales() + "人付款");
        helper.setGone(R.id.buy_now, item.getStatus() == 1);
        helper.setGone(R.id.has_no, item.getStatus() != 1);
    }

    static class MyGoodsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.goods_image)
        ImageView goodsImage;
        @BindView(R.id.goods_name)
        TextView goodsName;
        @BindView(R.id.goods_price)
        TextView goodsPrice;
        @BindView(R.id.buy_now)
        TextView buyNow;
        @BindView(R.id.has_no)
        ImageView hasNo;

        MyGoodsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
