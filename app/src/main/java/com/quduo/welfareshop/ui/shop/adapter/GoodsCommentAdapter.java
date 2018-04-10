package com.quduo.welfareshop.ui.shop.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.PreviewImageActivity;
import com.quduo.welfareshop.ui.friend.adapter.NineGridImageAdapter;
import com.quduo.welfareshop.ui.shop.entity.GoodsCommentInfo;
import com.w4lle.library.NineGridlayout;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:scene
 * Time:2018/2/28 16:02
 * Description:买家秀
 */

public class GoodsCommentAdapter extends BaseQuickAdapter<GoodsCommentInfo, BaseViewHolder> {
    private Activity activity;

    public GoodsCommentAdapter(Activity activity, List<GoodsCommentInfo> list) {
        super(R.layout.activity_goods_detail_comment_item, list);
        this.activity = activity;
    }

    @Override
    protected void convert(final BaseViewHolder helper, GoodsCommentInfo item) {
        final ArrayList<String> imageList = new ArrayList<>();
        for (String str : item.getImages()) {
            imageList.add(MyApplication.getInstance().getConfigInfo().getFile_domain() + str);
        }
        NineGridImageAdapter imageAdapter = new NineGridImageAdapter(activity, imageList);
        NineGridlayout imageLayout = helper.getView(R.id.image_layout);
        imageLayout.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(activity, PreviewImageActivity.class);
                intent.putExtra(PreviewImageActivity.ARG_URLS, imageList);
                intent.putExtra(PreviewImageActivity.ARG_POSITION, position);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
            }
        });
        imageLayout.setAdapter(imageAdapter);
        DateTime dateTime = new DateTime(item.getCreate_time() * 1000);
        String createTime = dateTime.toString("yyyy-MM-dd HH:mm:ss");
        helper.setText(R.id.nickname_and_time, item.getNick_name() + "/" + createTime);
        helper.setText(R.id.content, item.getContent());
    }

}
