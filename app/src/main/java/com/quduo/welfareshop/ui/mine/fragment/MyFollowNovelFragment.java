package com.quduo.welfareshop.ui.mine.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arjinmc.recyclerviewdecoration.RecyclerViewItemDecoration;
import com.blankj.utilcode.util.SizeUtils;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.mine.adapter.MyFollowNovelAdapter;
import com.quduo.welfareshop.ui.mine.presenter.MyFollowNovelPresenter;
import com.quduo.welfareshop.ui.mine.view.IMyFollowNovelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/3/1 11:18
 * Description:收藏的小说
 */

public class MyFollowNovelFragment extends BaseMvpFragment<IMyFollowNovelView, MyFollowNovelPresenter> implements IMyFollowNovelView {
    @BindView(R.id.recyclerView)
    LRecyclerView recyclerView;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private List<String> list;
    private LRecyclerViewAdapter mAdapter;

    public static MyFollowNovelFragment newInstance() {
        Bundle args = new Bundle();
        MyFollowNovelFragment fragment = new MyFollowNovelFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_follow_novel, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
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
    public void initView() {
        showContentPage();
        initRecyclerView();
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        MyFollowNovelAdapter adapter = new MyFollowNovelAdapter(getContext(), list);
        mAdapter = new LRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLoadMoreEnabled(false);
    }

    @Override
    public MyFollowNovelPresenter initPresenter() {
        return new MyFollowNovelPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
