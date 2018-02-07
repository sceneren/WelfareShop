package com.quduo.welfareshop.ui.friend.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.SizeUtils;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.itemDecoration.GridSpacingItemDecoration;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.friend.adapter.NearAdapter;
import com.quduo.welfareshop.ui.friend.presenter.NearPresenter;
import com.quduo.welfareshop.ui.friend.view.INearView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.loadmore.recyclerview.RecyclerAdapterWithHF;

/**
 * Author:scene
 * Time:2018/2/1 17:11
 * Description:附近的人
 */

public class NearFragment extends BaseMvpFragment<INearView, NearPresenter> implements INearView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;
    @BindView(R.id.friend_screen)
    ImageView friendScreen;

    public static NearFragment newInstance() {
        Bundle args = new Bundle();
        NearFragment fragment = new NearFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_near, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView() {
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ptrLayout.refreshComplete();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 2000);
            }
        });

        showContentPage();
        List<String> list = new ArrayList<>();
        list.add("xxxxxxxxxxx");
        list.add("xxxxxxxxxxx");
        list.add("xxxxxxxxxxx");
        list.add("xxxxxxxxxxx");
        list.add("xxxxxxxxxxx");
        list.add("xxxxxxxxxxx");
        list.add("xxxxxxxxxxx");
        list.add("xxxxxxxxxxx");
        list.add("xxxxxxxxxxx");
        list.add("xxxxxxxxxxx");
        list.add("xxxxxxxxxxx");
        list.add("xxxxxxxxxxx");
        list.add("xxxxxxxxxxx");
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, SizeUtils.dp2px(5), true));
        RecyclerAdapterWithHF mAdapter = new RecyclerAdapterWithHF(new NearAdapter(getContext(), list));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerAdapterWithHF.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerAdapterWithHF adapter, RecyclerView.ViewHolder vh, int position) {
                EventBus.getDefault().post(new StartBrotherEvent(OtherInfoFragment.newInstance(String.valueOf(position + 1))));
            }
        });

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

    @Override
    public NearPresenter initPresenter() {
        return new NearPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
