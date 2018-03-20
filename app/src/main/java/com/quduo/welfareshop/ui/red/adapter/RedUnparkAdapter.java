package com.quduo.welfareshop.ui.red.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.ui.red.entity.UnparkRedInfo;

import java.util.List;

public class RedUnparkAdapter extends BaseQuickAdapter<UnparkRedInfo, BaseViewHolder> {

    public RedUnparkAdapter(List<UnparkRedInfo> list) {
        super(R.layout.fragment_my_red_unpark_item, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, UnparkRedInfo item) {
        helper.setText(R.id.open, item.getStatus() == 1 ? "待开奖" : "可拆开");
    }
}
