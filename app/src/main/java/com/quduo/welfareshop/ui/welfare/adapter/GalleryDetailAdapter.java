package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.ImageDetailInfo;

import java.util.List;

import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * 图片详情
 */

public class GalleryDetailAdapter extends BaseQuickAdapter<ImageDetailInfo, BaseViewHolder> {
    private Context context;
    private List<ImageDetailInfo> list;

    public GalleryDetailAdapter(Context context, List<ImageDetailInfo> list) {
        super(R.layout.fragment_my_follow_image_item, list);
        this.context = context;
        this.list = list;

    }

    @Override
    protected void convert(BaseViewHolder helper, ImageDetailInfo item) {
        ImageView image = helper.getView(R.id.image);
        ViewGroup.LayoutParams params = image.getLayoutParams();
        float scale = (float) PtrLocalDisplay.SCREEN_WIDTH_PIXELS / (float) list.get(helper.getLayoutPosition()).getWidth();
        params.height = (int) (scale * (float) list.get(helper.getLayoutPosition()).getHeight());
        image.setLayoutParams(params);
        GlideApp.with(context)
                .asBitmap()
                .centerCrop()
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getUrl())
                .into(image);
    }

}
