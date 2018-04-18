package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.friend.entity.FriendOtherInfoDetailVideoInfo;
import com.quduo.welfareshop.widgets.RatioImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class OtherInfoVideoAdapter extends BaseAdapter {
    private Context context;
    private List<FriendOtherInfoDetailVideoInfo> list;
    private LayoutInflater inflater;

    public OtherInfoVideoAdapter(Context context, List<FriendOtherInfoDetailVideoInfo> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OtherInfoVideoViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_other_info_video_item, parent, false);
            holder = new OtherInfoVideoViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (OtherInfoVideoViewHolder) convertView.getTag();
        }
        FriendOtherInfoDetailVideoInfo info = list.get(position);
        MultiTransformation multi = new MultiTransformation(
                new CenterCrop(),
                new RoundedCornersTransformation(SizeUtils.dp2px(10), 0, RoundedCornersTransformation.CornerType.TOP));
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + info.getThumb())
                .placeholder(R.drawable.ic_default_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(multi))
                .into(holder.image);
        holder.playCount.setText(String.valueOf(info.getPlay_times()));
        holder.title.setText(info.getContent());
        return convertView;
    }

    static class OtherInfoVideoViewHolder {
        @BindView(R.id.image)
        RatioImageView image;
        @BindView(R.id.play_count)
        TextView playCount;
        @BindView(R.id.title)
        TextView title;

        OtherInfoVideoViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
