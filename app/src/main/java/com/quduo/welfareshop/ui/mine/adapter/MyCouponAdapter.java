package com.quduo.welfareshop.ui.mine.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.ui.mine.entity.CouponInfo;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/23 11:31
 * Description:我的代金券
 */

public class MyCouponAdapter extends BaseQuickAdapter<CouponInfo, BaseViewHolder> {

    public MyCouponAdapter(List<CouponInfo> list) {
        super(R.layout.fragment_my_coupon_item, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponInfo item) {
        helper.setText(R.id.price, item.getCost() + "元");
        DateTime dateTime = new DateTime(item.getExpress_time() * 1000);
        helper.setText(R.id.time, "有效期至：" + dateTime.toString("yyyy-MM-dd HH:mm"));
    }

}
