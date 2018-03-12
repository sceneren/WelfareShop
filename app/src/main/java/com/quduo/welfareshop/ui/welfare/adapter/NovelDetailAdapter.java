package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.ui.welfare.entity.NovelChapterInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/26 17:11
 * Description:小说详情
 */

public class NovelDetailAdapter extends BaseQuickAdapter<NovelChapterInfo, BaseViewHolder> {
    private Context context;

    public NovelDetailAdapter(Context context, List<NovelChapterInfo> list) {
        super(R.layout.fragment_welfare_novel_detail_item, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NovelChapterInfo item) {
        helper.setText(R.id.title, item.getTitle());
    }

}
