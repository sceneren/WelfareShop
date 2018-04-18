package com.quduo.welfareshop.ui.friend.fragment;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.quduo.welfareshop.MainFragment;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.BaseViewPagerAdapter;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.TabSelectedEvent;
import com.quduo.welfareshop.event.UnreadEvent;
import com.quduo.welfareshop.event.UpdateSessionEvent;
import com.quduo.welfareshop.greendao.dao.MessageInfoDao;
import com.quduo.welfareshop.mvp.BaseMainMvpFragment;
import com.quduo.welfareshop.ui.friend.entity.ChatMessageInfo;
import com.quduo.welfareshop.ui.friend.presenter.FriendPresenter;
import com.quduo.welfareshop.ui.friend.view.IFriendView;
import com.quduo.welfareshop.widgets.APSTSViewPager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.Instant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.weyye.hipermission.HiPermission;

/**
 * Author:scene
 * Time:2018/2/1 15:18
 * Description:
 */

public class FriendFragment extends BaseMainMvpFragment<IFriendView, FriendPresenter> implements IFriendView {

    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    APSTSViewPager viewPager;
    @BindView(R.id.unread_icon)
    ImageView unreadIcon;


    public static FriendFragment newInstance() {
        Bundle args = new Bundle();
        FriendFragment fragment = new FriendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
        toolbarTitle.setText(getString(R.string.tab_friend));
    }

    @Override
    public void initView() {
        super.initView();
        if (HiPermission.checkPermission(_mActivity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            initFragment();
        } else {
            //applyLocationPermission();
            ToastUtils.showShort("请先开启定位权限");
            EventBus.getDefault().post(new TabSelectedEvent(0));
        }
    }

    private void initFragment() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getParentFragment() instanceof MainFragment) {
                    String tabTitle[] = {"1对1视频", "热门视频", "消息", "关注"};
                    List<Fragment> fragmentList = new ArrayList<>();
                    fragmentList.add(NearFragment.newInstance());
                    fragmentList.add(HotVideoFragment.newInstance());
                    fragmentList.add(MessageFragment.newInstance());
                    fragmentList.add(FollowFragment.newInstance());

                    tab.addTab(tab.newTab().setText(tabTitle[0]));
                    tab.addTab(tab.newTab().setText(tabTitle[1]));
                    tab.addTab(tab.newTab().setText(tabTitle[2]));
                    tab.addTab(tab.newTab().setText(tabTitle[3]));

                    viewPager.setAdapter(new BaseViewPagerAdapter(getChildFragmentManager(), tabTitle, fragmentList));
                    viewPager.setOffscreenPageLimit(tabTitle.length);
                    tab.setupWithViewPager(viewPager);
                    sendUnReadCount();
                }

            }
        }, 500);

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
    public FriendPresenter initPresenter() {
        return new FriendPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void sendUnReadCount() {
        try {
            boolean isFirst = SPUtils.getInstance().getBoolean("isFirst", true);
            if (isFirst) {
                //保存一条系统数据
                ChatMessageInfo chatMessageInfo = new ChatMessageInfo();
                chatMessageInfo.setUserId(AppConfig.userId);
                chatMessageInfo.setOtherUserId("-1");
                chatMessageInfo.setOtherNickName("系统消息");
                chatMessageInfo.setOtherAvatar("");
                chatMessageInfo.setMessageType(0);
                chatMessageInfo.setMessageContent(MyApplication.getInstance().getConfigInfo().getPush_message());
                chatMessageInfo.setUnRead(1);
                chatMessageInfo.setIsOthersSend(1);
                Instant instant = new Instant();
                chatMessageInfo.setTime(instant.getMillis());
                chatMessageInfo.setAudioTime(0);
                MessageInfoDao.getInstance().insertUserData(chatMessageInfo);
                EventBus.getDefault().post(new UpdateSessionEvent());
                SPUtils.getInstance().put("isFirst", false);
            }
            if (MessageInfoDao.getInstance().hasUnReadMessage()) {
                unreadIcon.setVisibility(View.VISIBLE);
            }else{
                unreadIcon.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void setUnReadCount(UnreadEvent event) {
        try {
            unreadIcon.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
