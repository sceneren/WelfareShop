package com.quduo.welfareshop.ui.red.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.red.entity.RedOtherResultInfo;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/21 12:55
 * Description:红包详情
 */

public class RedDetailAdapter extends BaseQuickAdapter<RedOtherResultInfo, BaseViewHolder> {
    private Context context;

    public RedDetailAdapter(Context context, @Nullable List<RedOtherResultInfo> data) {
        super(R.layout.fragment_red_open_result_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RedOtherResultInfo item) {
        SelectableRoundedImageView avatar = helper.getView(R.id.avatar);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getAvatar())
                .into(avatar);
        helper.setText(R.id.nickname, item.getNickname());
        DateTime dateTime = new DateTime(item.getCreate_time() * 1000);
        helper.setText(R.id.time, dateTime.toString("mm-ss"));
        helper.setText(R.id.bonus, item.getBonus() + "元");
        helper.setVisible(R.id.best_luck, item.isIs_best());
    }
}
