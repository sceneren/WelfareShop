package com.quduo.welfareshop.ui.shop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/2/27 14:13
 * Description:商城
 */

public class ShopAdapter extends BaseQuickAdapter<String, ShopAdapter.ShopViewHolder> {
    private Context context;

    public ShopAdapter(Context context, List<String> list) {
        super(R.layout.fragment_shop_item, list);
        this.context = context;
    }

    @Override
    protected void convert(ShopViewHolder helper, String item) {
        String url = "http://pic19.nipic.com/20120214/3145425_134109747000_2.jpg";
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(url)
                .into(helper.image);
    }


    class ShopViewHolder extends BaseViewHolder {
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.btn_buy)
        TextView btnBuy;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.buy_number)
        TextView buyNumber;

        ShopViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            ViewGroup.LayoutParams layoutParams = image.getLayoutParams();
            int height = (int) ((PtrLocalDisplay.SCREEN_WIDTH_PIXELS - SizeUtils.dp2px(21)) / 2f);
            layoutParams.height = height;
            image.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) btnBuy.getLayoutParams();
            params.setMargins(0, height - SizeUtils.dp2px(9), 0, 0);
            btnBuy.setLayoutParams(params);
        }
    }
}
