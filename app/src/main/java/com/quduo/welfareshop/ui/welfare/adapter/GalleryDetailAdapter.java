package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.ImageDetailInfo;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

import static com.quduo.welfareshop.base.GlideOptions.bitmapTransform;

/**
 * 图片详情
 */

public class GalleryDetailAdapter extends BaseQuickAdapter<ImageDetailInfo, BaseViewHolder> {
    private Context context;
    private List<ImageDetailInfo> list;
    private boolean payed;

    public GalleryDetailAdapter(Context context, List<ImageDetailInfo> list, boolean payed) {
        super(R.layout.fragment_my_follow_image_item, list);
        this.context = context;
        this.list = list;
        this.payed = payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, ImageDetailInfo item) {
        ImageView image = helper.getView(R.id.image);
        ViewGroup.LayoutParams params = image.getLayoutParams();
        float scale = (float) PtrLocalDisplay.SCREEN_WIDTH_PIXELS / (float) list.get(helper.getLayoutPosition()).getWidth();
        params.height = (int) (scale * (float) list.get(helper.getLayoutPosition()).getHeight());
        image.setLayoutParams(params);
        if (helper.getLayoutPosition() > 7 && !payed) {
            helper.setVisible(R.id.cover_image, true);
            helper.setVisible(R.id.tip, true);
            GlideApp.with(context)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getUrl())
                    .placeholder(R.drawable.ic_default_image)
                    .centerCrop()
                    .apply(bitmapTransform(new BlurTransformation(15, 6)))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image);
        } else {
            helper.setVisible(R.id.cover_image, false);
            helper.setVisible(R.id.tip, false);
            GlideApp.with(context)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getUrl())
                    .placeholder(R.drawable.ic_default_image)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(image);
        }

    }

}
