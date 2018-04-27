package com.quduo.welfareshop.ui.welfare.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.welfare.presenter.LsjPresenter;
import com.quduo.welfareshop.ui.welfare.view.ILsjView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sunsky.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

public class LsjFragment extends BaseMvpFragment<ILsjView, LsjPresenter> implements ILsjView {
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.number_1)
    TextView number1;
    @BindView(R.id.number_2)
    TextView number2;
    @BindView(R.id.number_3)
    TextView number3;
    @BindView(R.id.number_4)
    TextView number4;
    @BindView(R.id.number_5)
    TextView number5;
    @BindView(R.id.refresh_layout1)
    SmartRefreshLayout refreshLayout1;
    @BindView(R.id.status_view1)
    StatusViewLayout statusView1;
    @BindView(R.id.pc_number)
    TextView pcNumber;
    @BindView(R.id.pc_web)
    TextView pcWeb;
    @BindView(R.id.pc_video)
    TextView pcVideo;
    @BindView(R.id.android_number)
    TextView androidNumber;
    @BindView(R.id.android_web)
    TextView androidWeb;
    @BindView(R.id.android_video)
    TextView androidVideo;
    @BindView(R.id.ios_number)
    TextView iosNumber;
    @BindView(R.id.ios_web)
    TextView iosWeb;
    @BindView(R.id.ios_video)
    TextView iosVideo;
    @BindView(R.id.refresh_layout2)
    SmartRefreshLayout refreshLayout2;
    Unbinder unbinder;

    private List<View> views = new ArrayList<>();

    public static LsjFragment newInstance() {
        Bundle args = new Bundle();
        LsjFragment fragment = new LsjFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare_lsj, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initView() {
        initRefreshLayout();
        List<String> list = new ArrayList<>();
        list.add("xxxxx111111");
        list.add("xxxxx222222");
        list.add("xxxxx333333");
        list.add("xxxxx444444");
        list.add("xxxxx555555");
        list.add("xxxxx666666");
        list.add("xxxxx777777");
        list.add("xxxxx888888");
        list.add("xxxxx999999");
        bindMarqueeView(list);
        showContentPage();

        refreshLayout1.setVisibility(View.VISIBLE);
        refreshLayout2.setVisibility(View.GONE);
    }

    private void initRefreshLayout() {
        refreshLayout1.setEnableLoadMore(false);
        refreshLayout1.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout1.finishRefresh(500);
            }
        });
        refreshLayout2.setEnableLoadMore(false);
        refreshLayout2.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout2.finishRefresh(500);
            }
        });
    }

    private void bindMarqueeView(List<String> list) {
        try {
            marqueeView.stopFlipping();
            views.clear();
            marqueeView.removeAllViews();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View itemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_welfare_lsj_item, marqueeView, false);
                TextView nickName = itemView.findViewById(R.id.nickname);
                nickName.setText(list.get(i));
                views.add(itemView);
            }
            marqueeView.setViews(views);
            marqueeView.startFlipping();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public LsjPresenter initPresenter() {
        return new LsjPresenter(this);
    }

    @Override
    public void showLoadingPage() {
        try {
            statusView1.showLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showContentPage() {
        try {
            statusView1.showContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showErrorPage() {
        try {
            statusView1.showFailed(retry1Listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener retry1Listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private String[] num2StringArray(int number) {
        String numberStr = String.valueOf(number);
        char[] chars = numberStr.toCharArray();
        String[] nums = new String[5];
        if (chars.length == 0) {
            nums[0] = String.valueOf(0);
            nums[1] = String.valueOf(0);
            nums[2] = String.valueOf(0);
            nums[3] = String.valueOf(0);
            nums[4] = String.valueOf(0);
        } else if (chars.length == 1) {
            nums[0] = String.valueOf(chars[0]);
            nums[1] = String.valueOf(0);
            nums[2] = String.valueOf(0);
            nums[3] = String.valueOf(0);
            nums[4] = String.valueOf(0);
        } else if (chars.length == 2) {
            nums[0] = String.valueOf(chars[0]);
            nums[1] = String.valueOf(chars[1]);
            nums[2] = String.valueOf(0);
            nums[3] = String.valueOf(0);
            nums[4] = String.valueOf(0);
        } else if (chars.length == 3) {
            nums[0] = String.valueOf(chars[0]);
            nums[1] = String.valueOf(chars[1]);
            nums[2] = String.valueOf(chars[2]);
            nums[3] = String.valueOf(0);
            nums[4] = String.valueOf(0);
        } else if (chars.length == 4) {
            nums[0] = String.valueOf(chars[0]);
            nums[1] = String.valueOf(chars[1]);
            nums[2] = String.valueOf(chars[2]);
            nums[3] = String.valueOf(chars[3]);
            nums[4] = String.valueOf(0);
        } else if (chars.length == 5) {
            nums[0] = String.valueOf(chars[0]);
            nums[1] = String.valueOf(chars[1]);
            nums[2] = String.valueOf(chars[2]);
            nums[3] = String.valueOf(chars[3]);
            nums[4] = String.valueOf(chars[4]);
        }
        return nums;
    }

    @OnClick(R.id.learn_now)
    public void onClickLearnNow() {
        refreshLayout1.setVisibility(View.GONE);
        refreshLayout2.setVisibility(View.VISIBLE);
    }
}
