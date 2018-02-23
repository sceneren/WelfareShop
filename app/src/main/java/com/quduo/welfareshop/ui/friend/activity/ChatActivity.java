package com.quduo.welfareshop.ui.friend.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.FollowEvent;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.friend.adapter.ChatAdapter;
import com.quduo.welfareshop.ui.friend.audio.AudioRecordButton;
import com.quduo.welfareshop.ui.friend.entity.ChatMessageInfo;
import com.quduo.welfareshop.ui.friend.presenter.ChatPresenter;
import com.quduo.welfareshop.ui.friend.userdef.QqUtils;
import com.quduo.welfareshop.ui.friend.userdef.SimpleUserDefAppsGridView;
import com.quduo.welfareshop.ui.friend.userdef.SimpleUserdefEmoticonsKeyBoard;
import com.quduo.welfareshop.ui.friend.view.IChatView;
import com.quduo.welfareshop.util.EmojiConstants;
import com.quduo.welfareshop.util.ImageSelectorUtil;
import com.quduo.welfareshop.util.SimpleCommonUtils;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;
import com.sj.emoji.EmojiBean;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.Instant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import sj.keyboard.data.EmoticonEntity;
import sj.keyboard.interfaces.EmoticonClickListener;
import sj.keyboard.widget.EmoticonsEditText;
import sj.keyboard.widget.FuncLayout;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.recyclerview.RecyclerAdapterWithHF;

/**
 * Author:scene
 * Time:2018/2/5 16:40
 * Description:聊天
 */
public class ChatActivity extends BaseMvpActivity<IChatView, ChatPresenter> implements IChatView, FuncLayout.OnFuncKeyBoardListener {

    private static final int REQUEST_GALLERY_CODE = 10002;
    private static final int REQUEST_CAMERA_CODE = 10001;

    @BindView(R.id.ek_bar)
    SimpleUserdefEmoticonsKeyBoard ekBar;
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
    private String otherId;
    private String otherNickName;
    private String otherAvatar;
    private boolean isFollow = false;

    private ChatAdapter chatAdapter;
    private RecyclerAdapterWithHF mAdapter;
    private List<ChatMessageInfo> messageList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        unbinder = ButterKnife.bind(this);
        otherId = getIntent().getStringExtra("ID");
        otherNickName = getIntent().getStringExtra("NICKNAME");
        otherAvatar = getIntent().getStringExtra("OTHERAVATAR");
        isFollow = getIntent().getBooleanExtra("IS_FOLLOW", false);
        initToolbar();
        initView();
        presenter.getAllMessage(false, true);
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
        initEmoticonsKeyBoardBar();

        followLayout.setVisibility(isFollow ? View.GONE : View.VISIBLE);
        othersNickname.setText(otherNickName);
        GlideApp.with(ChatActivity.this)
                .asBitmap()
                .centerCrop()
                .load(otherAvatar)
                .into(othersAvatar);

        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getAllMessage(true, false);
                ptrLayout.refreshComplete();
            }
        });
        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(ChatActivity.this, messageList);

        mAdapter = new RecyclerAdapterWithHF(chatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        ekBar.reset();
                        break;
                }
            }
        });
    }


    private void initEmoticonsKeyBoardBar() {
        QqUtils.initEmoticonsEditText(ekBar.getEtChat());
        ekBar.setAdapter(QqUtils.getCommonAdapter(this, emoticonClickListener));
        ekBar.addOnFuncKeyBoardListener(this);

        SimpleUserDefAppsGridView simpleUserDefAppsGridView = new SimpleUserDefAppsGridView(this);
        simpleUserDefAppsGridView.setOnClickItemListener(new SimpleUserDefAppsGridView.OnClickItemListener() {
            @Override
            public void onClickCamera() {
                ImageSelectorUtil.openCamera(ChatActivity.this, REQUEST_CAMERA_CODE);
            }

            @Override
            public void onClickGrallery() {
                ImageSelectorUtil.openImageList(ChatActivity.this, 9, REQUEST_GALLERY_CODE);
            }
        });
        ekBar.addFuncView(simpleUserDefAppsGridView);
        ekBar.getEtChat().setOnSizeChangedListener(new EmoticonsEditText.OnSizeChangedListener() {
            @Override
            public void onSizeChanged(int w, int h, int oldw, int oldh) {
                scrollToBottom();
            }
        });
        ekBar.getBtnSend().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSendBtnClick(ekBar.getEtChat().getText().toString());
                ekBar.getEtChat().setText("");
            }
        });
        if (ekBar.getBtnVoice() instanceof AudioRecordButton) {
            ((AudioRecordButton) ekBar.getBtnVoice()).setAudioFinishRecorderListener(new AudioRecordButton.AudioFinishRecorderListener() {
                @Override
                public void onStart() {
                    chatAdapter.stopPlayVoice();
                }

                @Override
                public void onFinished(float seconds, String filePath) {
                    ChatMessageInfo chatMessageInfo = new ChatMessageInfo();
                    chatMessageInfo.setUserId(AppConfig.userId);
                    chatMessageInfo.setOtherUserId(otherId);
                    chatMessageInfo.setOtherNickName(otherNickName);
                    chatMessageInfo.setOtherAvatar(otherAvatar);
                    chatMessageInfo.setMessageType(2);
                    chatMessageInfo.setMessageContent(filePath);
                    Instant instant = new Instant();
                    chatMessageInfo.setTime(instant.getMillis());
                    chatMessageInfo.setAudioTime(seconds);

                    presenter.sendMessage(chatMessageInfo);
                    messageList.add(chatMessageInfo);
                    mAdapter.notifyItemInserted(messageList.size() - 1);
                    recyclerView.smoothScrollToPosition(messageList.size() - 1);
                }
            });
        }

    }

    EmoticonClickListener emoticonClickListener = new EmoticonClickListener() {
        @Override
        public void onEmoticonClick(Object o, int actionType, boolean isDelBtn) {

            if (isDelBtn) {
                SimpleCommonUtils.delClick(ekBar.getEtChat());
            } else {
                if (o == null) {
                    return;
                }
                if (actionType == EmojiConstants.EMOTICON_CLICK_BIGIMAGE) {
                    if (o instanceof EmoticonEntity) {
                        onSendImage(((EmoticonEntity) o).getIconUri());
                    }
                } else {
                    String content = null;
                    if (o instanceof EmojiBean) {
                        content = ((EmojiBean) o).emoji;
                    } else if (o instanceof EmoticonEntity) {
                        content = ((EmoticonEntity) o).getContent();
                    }

                    if (TextUtils.isEmpty(content)) {
                        return;
                    }
                    int index = ekBar.getEtChat().getSelectionStart();
                    Editable editable = ekBar.getEtChat().getText();
                    editable.insert(index, content);
                }
            }
        }
    };

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
    public String getOtherUserId() {
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
    public String getOtherNickName() {
        return otherNickName;
    }

    @Override
    public void moveToBottom(boolean isFirstEnter) {
        try {
            if (messageList.size() > 0) {
                if (isFirstEnter) {
                    recyclerView.scrollToPosition(messageList.size() - 1);
                } else {
                    recyclerView.smoothScrollToPosition(messageList.size() - 1);
                }
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_GALLERY_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra("result");
            for (String path : pathList) {
                ChatMessageInfo chatMessageInfo = new ChatMessageInfo();
                chatMessageInfo.setUserId(AppConfig.userId);
                chatMessageInfo.setOtherUserId(otherId);
                chatMessageInfo.setOtherNickName(otherNickName);
                chatMessageInfo.setOtherAvatar(otherAvatar);
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
        } else if (requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK && data != null) {
            String path = data.getStringExtra("result"); // 图片地址
            ChatMessageInfo chatMessageInfo = new ChatMessageInfo();
            chatMessageInfo.setUserId(AppConfig.userId);
            chatMessageInfo.setOtherUserId(otherId);
            chatMessageInfo.setOtherNickName(otherNickName);
            chatMessageInfo.setOtherAvatar(otherAvatar);
            chatMessageInfo.setMessageType(1);
            chatMessageInfo.setMessageContent(path);
            Instant instant = new Instant();
            chatMessageInfo.setTime(instant.getMillis());
            chatMessageInfo.setAudioTime(0);


            presenter.sendMessage(chatMessageInfo);
            messageList.add(chatMessageInfo);
            mAdapter.notifyItemInserted(messageList.size() - 1);
            recyclerView.smoothScrollToPosition(messageList.size() - 1);
        }
    }


    @Override
    public void OnFuncPop(int height) {

    }

    @Override
    public void OnFuncClose() {

    }

    private void onSendBtnClick(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            //发送消息
            ChatMessageInfo chatMessageInfo = new ChatMessageInfo();
            chatMessageInfo.setUserId(AppConfig.userId);
            chatMessageInfo.setOtherUserId(otherId);
            chatMessageInfo.setOtherNickName(otherNickName);
            chatMessageInfo.setOtherAvatar(otherAvatar);
            chatMessageInfo.setMessageType(0);
            chatMessageInfo.setMessageContent(msg);
            Instant instant = new Instant();
            chatMessageInfo.setTime(instant.getMillis());
            chatMessageInfo.setAudioTime(0);

            presenter.sendMessage(chatMessageInfo);
            messageList.add(chatMessageInfo);
            mAdapter.notifyItemInserted(messageList.size() - 1);
            scrollToBottom();
        }
    }

    private void scrollToBottom() {
        recyclerView.requestLayout();
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.smoothScrollToPosition(messageList.size() - 1);
            }
        });
    }

    private void onSendImage(String image) {
        if (!TextUtils.isEmpty(image)) {
            scrollToBottom();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        ekBar.reset();
    }

    @Override
    protected void onDestroy() {
        try {
            messageList.clear();
            mAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
        unbinder.unbind();
    }

}
