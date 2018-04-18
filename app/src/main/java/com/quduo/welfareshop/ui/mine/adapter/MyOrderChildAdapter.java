package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.mine.entity.OrderInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 9:05
 * Description:订单子页面
 */

public class MyOrderChildAdapter extends BaseQuickAdapter<OrderInfo, BaseViewHolder> {
    private static final int TYPE_UNPAY = 0;//待付款
    private static final int TYPE_UNSEND = 1;//待发货
    private static final int TYPE_UNRECEIVER = 2;//待收货
    private Context context;

    public MyOrderChildAdapter(Context context, List<OrderInfo> list) {
        super(list);
        this.context = context;
        setMultiTypeDelegate(new MultiTypeDelegate<OrderInfo>() {
            @Override
            protected int getItemType(OrderInfo entity) {
                //根据你的实体类来判断布局类型
                int type;
                switch (entity.getStatus()) {
                    case 1:
                        type = TYPE_UNPAY;
                        break;
                    case 2:
                        type = TYPE_UNSEND;
                        break;
                    case 3:
                        type = TYPE_UNRECEIVER;
                        break;
                    default:
                        type = TYPE_UNPAY;
                        break;
                }
                return type;
            }
        });
        getMultiTypeDelegate()
                .registerItemType(TYPE_UNPAY, R.layout.fragment_my_order_unpay_item)
                .registerItemType(TYPE_UNSEND, R.layout.fragment_my_order_unsend_item)
                .registerItemType(TYPE_UNRECEIVER, R.layout.fragment_my_order_unreceiver_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderInfo item) {
        if (helper.getItemViewType() == TYPE_UNPAY) {
            helper.setText(R.id.total_number, "共" + item.getNumber() + "件商品");
            helper.setText(R.id.total_price, "￥" + item.getActual_pay());
            Number num = Float.parseFloat(item.getCost()) * 100;
            int giveNum = num.intValue() / 100;
            helper.setText(R.id.total_score, "(免邮费)送" + giveNum + "积分" + giveNum + "钻石");
            helper.addOnClickListener(R.id.cancel_order);
        }
        ImageView goodsImage = helper.getView(R.id.goods_image);
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getThumb())
                .placeholder(R.drawable.ic_default_shop)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(goodsImage);
        helper.setText(R.id.goods_name, item.getName());
        helper.setText(R.id.goods_price, "￥" + item.getPrice());
        helper.setText(R.id.goods_number, "X" + item.getNumber());
        helper.setText(R.id.goods_model, item.getModel());
    }
}
