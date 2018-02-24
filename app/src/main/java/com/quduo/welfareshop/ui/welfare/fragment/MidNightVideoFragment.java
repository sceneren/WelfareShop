package com.quduo.welfareshop.ui.welfare.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.welfare.adapter.MidnightVideoAdapter;
import com.quduo.welfareshop.ui.welfare.presenter.MidNightVideoPresenter;
import com.quduo.welfareshop.ui.welfare.view.IMidNightVideoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/2/24 10:11
 * Description:午夜影院
 */
public class MidNightVideoFragment extends BaseMvpFragment<IMidNightVideoView, MidNightVideoPresenter> implements IMidNightVideoView {

    @BindView(R.id.recyclerView)
    LRecyclerView recyclerView;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private List<String> list;
    private LRecyclerViewAdapter mAdapter;

    public static MidNightVideoFragment newInstance() {
        Bundle args = new Bundle();
        MidNightVideoFragment fragment = new MidNightVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare_midnight_video, container, false);
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
        initFooterView();
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        MidnightVideoAdapter adapter = new MidnightVideoAdapter(getContext(), list);
        mAdapter = new LRecyclerViewAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    private void initFooterView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_bottom_more_content, null);
        mAdapter.addFooterView(view);
    }

    @Override
    public MidNightVideoPresenter initPresenter() {
        return new MidNightVideoPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
