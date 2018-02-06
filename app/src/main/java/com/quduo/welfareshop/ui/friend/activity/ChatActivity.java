package com.quduo.welfareshop.ui.friend.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.event.FollowEvent;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.friend.adapter.ChatAdapter;
import com.quduo.welfareshop.ui.friend.entity.ChatMessageInfo;
import com.quduo.welfareshop.ui.friend.presenter.ChatPresenter;
import com.quduo.welfareshop.ui.friend.view.IChatView;
import com.quduo.welfareshop.util.ImageSelectorUtil;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.Instant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.recyclerview.RecyclerAdapterWithHF;

/**
 * Author:scene
 * Time:2018/2/5 16:40
 * Description:聊天
 */
public class ChatActivity extends BaseMvpActivity<IChatView, ChatPresenter> implements IChatView {

    private static final int REQUEST_GALLERY_CODE = 10002;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    @BindView(R.id.others_avatar)
    SelectableRoundedImageView othersAvatar;
    @BindView(R.id.others_nickname)
    TextView othersNickname;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.follow_layout)
    RelativeLayout followLayout;
    @BindView(R.id.follow)
    TextView follow;
    @BindView(R.id.btn_audio)
    ImageView btnAudio;
    @BindView(R.id.edit_content)
    EditText editContent;
    @BindView(R.id.btn_emoji)
    ImageView btnEmoji;
    @BindView(R.id.btn_image)
    ImageView btnImage;
    private int otherId = 0;
    private String otherNickName;
    private boolean isFollow = false;


    private RecyclerAdapterWithHF mAdapter;
    private List<ChatMessageInfo> messageList;

    private boolean hasSend = false;
    private boolean hasInitChoosePhoto = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        unbinder = ButterKnife.bind(this);
        otherId = getIntent().getIntExtra("ID", 0);
        otherNickName = getIntent().getStringExtra("NICKNAME");
        isFollow = getIntent().getBooleanExtra("IS_FOLLOW", false);
        initToolbar();
        initView();
        presenter.getAllMessage(false);
    }

    private void initToolbar() {
        toolbarTitle.setText(otherNickName);
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        followLayout.setVisibility(isFollow ? View.GONE : View.VISIBLE);
        othersNickname.setText(otherNickName);
        String url = "http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg";
        GlideApp.with(ChatActivity.this)
                .asBitmap()
                .centerCrop()
                .load(url)
                .into(othersAvatar);

        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getAllMessage(true);
                ptrLayout.refreshComplete();
            }
        });
        messageList = new ArrayList<>();
        ChatAdapter chatAdapter = new ChatAdapter(ChatActivity.this, messageList);
        mAdapter = new RecyclerAdapterWithHF(chatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
        recyclerView.setAdapter(mAdapter);

        //初始化底部输入栏
        editContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editContent.getText().toString().length() > 0) {
                    btnImage.setImageResource(R.drawable.ic_chat_bottom_send);
                    hasSend = true;
                } else {
                    btnImage.setImageResource(R.drawable.ic_chat_bottom_image);
                    hasSend = false;
                }
            }
        });

    }

    @Override
    public void showLoadingPage() {

    }

    @Override
    public void showContentPage() {

    }

    @Override
    public void showErrorPage() {

    }

    @Override
    public ChatPresenter initPresenter() {
        return new ChatPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public long getOtherUserId() {
        return otherId;
    }

    @Override
    public void refreshComplete() {
        try {
            ptrLayout.refreshComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecyclerView(List<ChatMessageInfo> list) {
        try {
            this.messageList.clear();
            this.messageList.addAll(list);
            mAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getEditContentStr() {
        return editContent.getText().toString();
    }

    @Override
    public String getOtherNickName() {
        return otherNickName;
    }

    @Override
    public void moveToBottom() {
        try {
            if (messageList.size() > 0) {
                recyclerView.smoothScrollToPosition(messageList.size() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.follow)
    public void onClickFollow() {
        EventBus.getDefault().post(new FollowEvent());
        followLayout.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_image)
    public void onClickBtnImage() {
        if (hasSend) {
            //发送消息
            ChatMessageInfo chatMessageInfo = new ChatMessageInfo();
            chatMessageInfo.setOtherUserId(otherId);
            chatMessageInfo.setOtherNickName(otherNickName);
            chatMessageInfo.setMessageType(0);
            chatMessageInfo.setMessageContent(editContent.getText().toString());
            Instant instant = new Instant();
            chatMessageInfo.setTime(instant.getMillis());
            chatMessageInfo.setAudioTime(0);
            presenter.sendMessage(chatMessageInfo);
            messageList.add(chatMessageInfo);
            mAdapter.notifyItemInserted(messageList.size() - 1);
            editContent.setText("");
            recyclerView.smoothScrollToPosition(messageList.size() - 1);
        } else {
            //添加图片
            if (!hasInitChoosePhoto) {
                initPhotoImageLoader();
            }
            ImageSelectorUtil.openImageList(ChatActivity.this, 9, REQUEST_GALLERY_CODE);
        }
    }

    // 自定义图片加载器
    private void initPhotoImageLoader() {
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                GlideApp.with(context).load(path).into(imageView);
            }
        });
        hasInitChoosePhoto = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra("result");
            for (String path : pathList) {
                ChatMessageInfo chatMessageInfo = new ChatMessageInfo();
                chatMessageInfo.setOtherUserId(otherId);
                chatMessageInfo.setOtherNickName(otherNickName);
                chatMessageInfo.setMessageType(1);
                chatMessageInfo.setMessageContent(path);
                Instant instant = new Instant();
                chatMessageInfo.setTime(instant.getMillis());
                chatMessageInfo.setAudioTime(0);
                presenter.sendMessage(chatMessageInfo);
                messageList.add(chatMessageInfo);
            }
            mAdapter.notifyItemInserted(messageList.size() - 1);
            recyclerView.smoothScrollToPosition(messageList.size() - 1);
        }
    }
}
