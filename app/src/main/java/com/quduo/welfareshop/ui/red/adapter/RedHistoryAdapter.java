package com.quduo.welfareshop.ui.red.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.ui.red.entity.RedHistoryDetailInfo;

import org.joda.time.DateTime;

import java.util.List;

public class RedHistoryAdapter extends BaseQuickAdapter<RedHistoryDetailInfo, BaseViewHolder> {

    public RedHistoryAdapter(List<RedHistoryDetailInfo> list) {
        super(R.layout.fragment_my_red_history_item, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, RedHistoryDetailInfo item) {
        DateTime dateTime = new DateTime(item.getCreate_time() * 1000);
        helper.setText(R.id.time, dateTime.toString("yyyy-MM-dd"));
        helper.setText(R.id.money, item.getBonus() + "元");
    }
}
