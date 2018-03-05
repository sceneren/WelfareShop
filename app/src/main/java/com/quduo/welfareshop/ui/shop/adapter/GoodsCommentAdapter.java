package com.quduo.welfareshop.ui.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.PreviewImageActivity;
import com.quduo.welfareshop.ui.friend.adapter.NineGridImageAdapter;
import com.w4lle.library.NineGridlayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:scene
 * Time:2018/2/28 16:02
 * Description:买家秀
 */

public class GoodsCommentAdapter extends BaseQuickAdapter<String, GoodsCommentAdapter.GoodsCommentViewHolder> {
    private Context context;
    private List<String> list;

    public GoodsCommentAdapter(Context context, List<String> list) {
        super(R.layout.activity_goods_detail_comment_item, list);
        this.context = context;
        this.list = list;
    }


    @Override
    protected void convert(GoodsCommentViewHolder helper, String item) {
        String url = "http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg";
        final ArrayList<String> imageList = new ArrayList<>();
        for (int i = 0; i < (helper.getLayoutPosition() + 1); i++) {
            imageList.add(url);
        }
        NineGridImageAdapter imageAdapter = new NineGridImageAdapter(context, imageList);
        helper.imageLayout.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(context, PreviewImageActivity.class);
                intent.putExtra(PreviewImageActivity.ARG_URLS, imageList);
                intent.putExtra(PreviewImageActivity.ARG_POSITION, position);
                context.startActivity(intent);
            }
        });
        helper.imageLayout.setAdapter(imageAdapter);
    }


    class GoodsCommentViewHolder extends BaseViewHolder {
        @BindView(R.id.nickname_and_time)
        TextView nicknameAndTime;
        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.image_layout)
        NineGridlayout imageLayout;

        GoodsCommentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
