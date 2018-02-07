package com.quduo.welfareshop.ui.friend.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.event.FollowEvent;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.friend.activity.ChatActivity;
import com.quduo.welfareshop.ui.friend.adapter.OtherInfoImageAdapter;
import com.quduo.welfareshop.ui.friend.presenter.OtherInfoPresenter;
import com.quduo.welfareshop.ui.friend.view.IOtherInfoView;
import com.quduo.welfareshop.widgets.CustomeGridView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:scene
 * Time:2018/2/5 14:57
 * Description:别人的主页
 */
public class OtherInfoFragment extends BaseBackMvpFragment<IOtherInfoView, OtherInfoPresenter> implements IOtherInfoView {
    private static final String ARG_ID = "user_id";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.user_id)
    TextView userId;
    @BindView(R.id.fans_num)
    TextView fansNum;
    @BindView(R.id.no_photo)
    TextView noPhoto;
    @BindView(R.id.photoGridView)
    CustomeGridView photoGridView;
    Unbinder unbinder;
    @BindView(R.id.send_message)
    TextView sendMessage;
    @BindView(R.id.follow)
    TextView follow;
    private String otherUserId;

    public static OtherInfoFragment newInstance(String otherUserId) {
        Bundle args = new Bundle();
        args.putString(ARG_ID, otherUserId);
        OtherInfoFragment fragment = new OtherInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (getArguments() != null) {
            otherUserId = getArguments().getString(ARG_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other_info, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
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
    public void initToolbar() {
        initToolbarNav(toolbar);
    }

    @Override
    public void initView() {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        OtherInfoImageAdapter adapter = new OtherInfoImageAdapter(getContext(), list);
        photoGridView.setAdapter(adapter);
        photoGridView.setVisibility(View.VISIBLE);
        noPhoto.setVisibility(View.GONE);
    }

    @Override
    public OtherInfoPresenter initPresenter() {
        return new OtherInfoPresenter(this);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.send_message)
    public void onClickSendMessage() {
        Intent intent = new Intent(_mActivity, ChatActivity.class);
        intent.putExtra("ID", otherUserId);
        intent.putExtra("NICKNAME", "小周" + otherUserId);
        intent.putExtra("IS_FOLLOW", !follow.getText().toString().equals("关注"));
        intent.putExtra("OTHERAVATAR", "http://e.hiphotos.baidu.com/image/pic/item/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg");
        startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @OnClick(R.id.follow)
    public void onClickFollow() {
        if (follow.getText().toString().equals("关注")) {
            follow.setText("已关注");
            follow.setTextColor(ContextCompat.getColor(getContext(), R.color.theme_color));
            follow.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(getContext(), R.drawable.ic_guanzhu_s), null, null);
        } else {
            follow.setText("关注");
            follow.setTextColor(ContextCompat.getColor(getContext(), R.color.content_text_color));
            follow.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(getContext(), R.drawable.ic_guanzhu_d), null, null);
        }
    }

    @Subscribe
    public void currentUserHasFollow(FollowEvent event) {
        follow.setText("已关注");
        follow.setTextColor(ContextCompat.getColor(getContext(), R.color.theme_color));
        follow.setCompoundDrawablesWithIntrinsicBounds(null, ContextCompat.getDrawable(getContext(), R.drawable.ic_guanzhu_s), null, null);
    }


}
