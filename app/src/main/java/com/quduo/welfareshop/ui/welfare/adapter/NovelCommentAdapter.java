package com.quduo.welfareshop.ui.welfare.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.welfare.entity.NovelCommentInfo;

import org.joda.time.DateTime;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NovelCommentAdapter extends BaseQuickAdapter<NovelCommentInfo, BaseViewHolder> {
    private Context context;

    public NovelCommentAdapter(Context context, @Nullable List<NovelCommentInfo> data) {
        super(R.layout.fragment_welfare_novel_comment_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, NovelCommentInfo item) {
        helper.setText(R.id.nickname, item.getNickname());
        helper.setText(R.id.content, item.getContent());
        helper.setText(R.id.time, new DateTime(item.getCreate_time() * 1000).toString("MM-dd HH:mm"));
        GlideApp.with(context)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getAvatar())
                .placeholder(R.drawable.ic_default_avatar)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into((ImageView) helper.getView(R.id.avatar));
    }

    static class ViewHolder {
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.nickname)
        TextView nickname;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.content)
        TextView content;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
