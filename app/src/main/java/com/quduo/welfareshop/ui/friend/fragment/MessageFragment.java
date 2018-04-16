package com.quduo.welfareshop.ui.friend.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.UnreadEvent;
import com.quduo.welfareshop.event.UpdateSessionEvent;
import com.quduo.welfareshop.greendao.dao.MessageInfoDao;
import com.quduo.welfareshop.itemDecoration.SpacesItemDecoration;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.friend.activity.ChatActivity;
import com.quduo.welfareshop.ui.friend.adapter.MessageAdapter;
import com.quduo.welfareshop.ui.friend.entity.ChatMessageInfo;
import com.quduo.welfareshop.ui.friend.presenter.MessagePresenter;
import com.quduo.welfareshop.ui.friend.view.IMessageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/2/1 17:11
 * Description:消息
 */

public class MessageFragment extends BaseMvpFragment<IMessageView, MessagePresenter> implements IMessageView {
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;

    private MessageAdapter adapter;
    private List<ChatMessageInfo> list;

    public MessageFragment() {
    }

    public static MessageFragment newInstance() {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_message, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView() {
        MyApplication.getInstance().uploadPageInfo(AppConfig.POSITION_FRIEND_MESSAGE, 0);
        showContentPage();
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getAllSeesion();
            }
        });

        list = new ArrayList<>();
        adapter = new MessageAdapter(getContext(), list);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (list.get(position).getOtherNickName().equals("系统消息")) {
                    EventBus.getDefault().post(new UnreadEvent(0));
                }
                if (view.getId() == R.id.content_layout) {
                    Intent intent = new Intent(_mActivity, ChatActivity.class);
                    intent.putExtra("ID", list.get(position).getOtherUserId());
                    intent.putExtra("NICKNAME", list.get(position).getOtherNickName());
                    intent.putExtra("IS_FOLLOW", true);
                    intent.putExtra("NEARBY", true);
                    intent.putExtra("OTHERAVATAR", list.get(position).getOtherAvatar());
                    startActivity(intent);
                    _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
                    if (list.get(position).getUnRead()==1) {
                        ChatMessageInfo messageInfo = list.get(position);
                        messageInfo.setUnRead(0);
                        adapter.notifyItemChanged(position);
                        MessageInfoDao.getInstance().updateUserData(messageInfo);
                    }
                } else if (view.getId() == R.id.delete) {
                    presenter.deleteSession(AppConfig.userId, list.get(position).getOtherUserId());
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(1)));
        recyclerView.setAdapter(adapter);
        presenter.getAllSeesion();
        View notDataView = getLayoutInflater().inflate(R.layout.layout_empty, (ViewGroup) recyclerView.getParent(), false);
        adapter.setEmptyView(notDataView);
    }

    @Override
    public void showLoadingPage() {
        try {
            statusView.showLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showContentPage() {
        try {
            statusView.showContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showErrorPage() {
        try {
            statusView.showFailed(retryListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    public MessagePresenter initPresenter() {
        return new MessagePresenter(this);
    }

    @Override
    public void onDestroyView() {
        try {
            EventBus.getDefault().unregister(this);
            list.clear();
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getAllSessionInfoSuccess(List<ChatMessageInfo> list) {
        try {
            refreshLayout.finishRefresh();
            this.list.clear();
            this.list.addAll(list);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void updateSession(UpdateSessionEvent event) {
        try {
            presenter.getAllSeesion();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
