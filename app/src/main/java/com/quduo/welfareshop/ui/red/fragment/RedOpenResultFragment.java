package com.quduo.welfareshop.ui.red.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.itemDecoration.SpacesItemDecoration;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.red.adapter.RedDetailAdapter;
import com.quduo.welfareshop.ui.red.entity.RedOpenResultInfo;
import com.quduo.welfareshop.ui.red.entity.RedOtherResultInfo;
import com.quduo.welfareshop.ui.red.presenter.RedOpenResultPresenter;
import com.quduo.welfareshop.ui.red.view.IRedOpenResultView;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/3/21 12:28
 * Description:红包开奖结果
 */

public class RedOpenResultFragment extends BaseBackMvpFragment<IRedOpenResultView, RedOpenResultPresenter> implements IRedOpenResultView {
    private static final String ARG_ID = "id";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private int redId;
    private TextView bonus;
    private TextView totalNumber;
    private TextView totalMoney;

    private List<RedOtherResultInfo> list = new ArrayList<>();
    private RedDetailAdapter adapter;

    public static RedOpenResultFragment newInstance(int redId) {
        Bundle args = new Bundle();
        args.putInt(ARG_ID, redId);
        RedOpenResultFragment fragment = new RedOpenResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            redId = getArguments().getInt(ARG_ID, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_red_open_result, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
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
            presenter.getData(redId);
        }
    };

    @Override
    public void showMessage(String message) {
        try {
            ToastUtils.showShort(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bindData(RedOpenResultInfo data) {
        try {
            list.clear();
            list.addAll(data.getBonus());
            adapter.notifyDataSetChanged();

            bonus.setText(data.getData().getBonus());
            totalNumber.setText(String.valueOf(data.getData().getCount()));
            totalMoney.setText(String.valueOf(data.getData().getPool()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText("现金红包");
        toolbar.setNavigationIcon(R.drawable.ic_back_gold);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    public void initView() {
        initRecyclerView();
        initHeaderView();
        presenter.getData(redId);
    }

    private void initRecyclerView() {
        adapter = new RedDetailAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(1)));
        recyclerView.setAdapter(adapter);
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_red_open_result_header, recyclerView, false);
        SelectableRoundedImageView avatar = headerView.findViewById(R.id.avatar);
        TextView nickname = headerView.findViewById(R.id.nickname);
        bonus = headerView.findViewById(R.id.bonus);
        totalNumber = headerView.findViewById(R.id.total_number);
        totalMoney = headerView.findViewById(R.id.total_money);
        GlideApp.with(this)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + MyApplication.getInstance().getUserInfo().getAvatar())
                .into(avatar);
        nickname.setText(MyApplication.getInstance().getUserInfo().getNickname());

        adapter.addHeaderView(headerView);
    }

    @Override
    public RedOpenResultPresenter initPresenter() {
        return new RedOpenResultPresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.RED_DETAIL_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }
}
