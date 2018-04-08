package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.ui.friend.entity.ChatMessageInfo;
import com.quduo.welfareshop.ui.friend.userdef.QqUtils;
import com.quduo.welfareshop.util.Time2StringUtils;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Author:scene
 * Time:2018/2/2 11:47
 * Description:我的消息
 */
public class MessageAdapter extends BaseQuickAdapter<ChatMessageInfo, BaseViewHolder> {
    private Context context;

    public MessageAdapter(Context context, List<ChatMessageInfo> list) {
        super(R.layout.fragment_friend_message_item, list);
        this.context = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, ChatMessageInfo item) {
        ImageView avatar = helper.getView(R.id.avatar);
        String url = item.getOtherAvatar();
        if (!url.startsWith("http")) {
            url = MyApplication.getInstance().getConfigInfo().getFile_domain() + item.getOtherAvatar();
        }
        MultiTransformation multiTransformation = new MultiTransformation(
                new CenterCrop(), new RoundedCornersTransformation(SizeUtils.dp2px(5), 0)
        );
        GlideApp.with(context)
                .load(url)
                .placeholder(R.drawable.ic_default_avatar)
                .apply(RequestOptions.bitmapTransform(multiTransformation))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(avatar);
        helper.setText(R.id.nickname, item.getOtherNickName());
        TextView message = helper.getView(R.id.message);
        if (item.getMessageType() == 0) {
            QqUtils.spannableEmoticonFilter(message, item.getMessageContent());
        } else if (item.getMessageType() == 1) {
            message.setText("【图片】");
        } else {
            message.setText("【语音】");
        }
        helper.setText(R.id.time, Time2StringUtils.millisDistanceCurrent(item.getTime()));
        helper.addOnClickListener(R.id.content_layout);
        helper.addOnClickListener(R.id.delete);
    }
}
