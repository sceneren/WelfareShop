package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.friend.entity.NearInfo;
import com.quduo.welfareshop.widgets.CustomHeightRoundedImageView;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/1 18:00
 * Description:附近的人
 */

public class NearAdapter extends BaseQuickAdapter<NearInfo, BaseViewHolder> {
    private Context context;

    public NearAdapter(Context context, List<NearInfo> list) {
        super(R.layout.fragment_friend_near_item, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NearInfo item) {
        helper.setImageResource(R.id.sex, item.getSex() == 1 ? R.drawable.ic_male : R.drawable.ic_female);
        helper.setText(R.id.age, item.getSex() + "岁");
        helper.setText(R.id.name, item.getNickname());
        helper.setText(R.id.distance, item.getDistance() + "m");
        CustomHeightRoundedImageView image = helper.getView(R.id.image);
        GlideApp.with(context)
                .asBitmap()
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getAvatar())
                .centerCrop()
                .into(image);
    }

}
