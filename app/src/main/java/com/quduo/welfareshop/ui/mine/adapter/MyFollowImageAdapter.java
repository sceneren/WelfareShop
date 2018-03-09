package com.quduo.welfareshop.ui.mine.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.ImageDetailInfo;

import java.util.List;

import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * Author:scene
 * Time:2018/3/1 11:41
 * Description:我收藏的图片
 */

public class MyFollowImageAdapter extends BaseQuickAdapter<ImageDetailInfo, BaseViewHolder> {
    private Context context;
    private List<ImageDetailInfo> list;

    public MyFollowImageAdapter(Context context, List<ImageDetailInfo> list) {
        super(R.layout.fragment_my_follow_image_item, list);
        this.context = context;
        this.list = list;

    }

    @Override
    protected void convert(BaseViewHolder helper, ImageDetailInfo item) {
        int itemWidth = PtrLocalDisplay.SCREEN_WIDTH_PIXELS / 2 - SizeUtils.dp2px(5);
        ImageView image = helper.getView(R.id.image);
        ViewGroup.LayoutParams params = image.getLayoutParams();
        float scale = (float) itemWidth / (float) list.get(helper.getLayoutPosition()).getWidth();
        params.width = itemWidth;
        params.height = (int) (scale * (float) list.get(helper.getLayoutPosition()).getHeight());
        image.setLayoutParams(params);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getUrl())
                .into(image);
    }


}
