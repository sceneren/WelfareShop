package com.quduo.welfareshop.ui.red.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.dialog.RedOpenDialog;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpFragment;
import com.quduo.welfareshop.ui.red.adapter.RedUnparkAdapter;
import com.quduo.welfareshop.ui.red.entity.OpenRedResultInfo;
import com.quduo.welfareshop.ui.red.entity.UnparkRedInfo;
import com.quduo.welfareshop.ui.red.presenter.UnparkRedPresenter;
import com.quduo.welfareshop.ui.red.view.IUnparkRedView;
import com.quduo.welfareshop.widgets.SelectableRoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/3/8 9:59
 * Description:未拆开的红包
 */

public class UnparkRedFragment extends BaseMvpFragment<IUnparkRedView, UnparkRedPresenter> implements IUnparkRedView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private List<UnparkRedInfo> list = new ArrayList<>();
    private RedUnparkAdapter adapter;

    private SelectableRoundedImageView avatar;
    private TextView money;
    private LinearLayout cash;

    private RedOpenDialog redOpenDialog;

    public static UnparkRedFragment newInstance() {
        Bundle args = new Bundle();
        UnparkRedFragment fragment = new UnparkRedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_red_unpark, container, false);
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
            presenter.getData(true);
        }
    };

    @Override
    public void initView() {
        initRecyclerView();
        initHeaderView();
        presenter.getData(true);
    }

    private void initRecyclerView() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getData(false);
            }
        });
        adapter = new RedUnparkAdapter(list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (list.get(position).getStatus() != 1) {
                    showOpenRedDialog(list.get(position).getPool(), position);
                }
            }
        });
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_my_red_unpark_header, null);
        avatar = headerView.findViewById(R.id.avatar);
        money = headerView.findViewById(R.id.money);
        cash = headerView.findViewById(R.id.cash);
        adapter.addHeaderView(headerView);

        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCashFragment();
            }
        });

        GlideApp.with(this)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + MyApplication.getInstance().getUserInfo().getAvatar())
                .into(avatar);
        money.setText(MessageFormat.format("{0}元", MyApplication.getInstance().getUserInfo().getMoney()));
    }

    @Override
    public UnparkRedPresenter initPresenter() {
        return new UnparkRedPresenter(this);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.UNPARK_RED_TAG);
        OkGo.getInstance().cancelTag(ApiUtil.OPEN_RED_TAG);
        super.onDestroyView();
        unbinder.unbind();
    }

    private void toCashFragment() {
        if ((getParentFragment()) != null && getParentFragment() instanceof MyRedFragment) {
            ((MyRedFragment) getParentFragment()).start(CashFragment.newInstance());
        }
    }

    @Override
    public void showMessage(String message) {
        try {
            ToastUtils.showShort(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showLoadingDialog() {
        try {
            StyledDialog.buildLoading().setActivity(_mActivity).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideLoadingDialog() {
        try {
            StyledDialog.dismissLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refreshFinish() {
        try {
            refreshLayout.finishRefresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bindData(List<UnparkRedInfo> data) {
        try {
            list.clear();
            list.addAll(data);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void openRedSuccess(int position, OpenRedResultInfo data) {
        try {
            hideOpenRedDialog();
            list.remove(position);
            adapter.notifyDataSetChanged();
            money.setText(data.getMoney());
            showOpenRedSuccessDialog(data.getBonus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showOpenRedDialog(String totalMoney, final int position) {
        try {
            if (redOpenDialog == null) {
                redOpenDialog = new RedOpenDialog(_mActivity);
            }
            redOpenDialog.setOnClickOpenListener(new RedOpenDialog.OnClickOpenListener() {
                @Override
                public void onClickOpen() {
                    presenter.openRed(position, list.get(position).getId());
                }
            });
            redOpenDialog.showDialog(totalMoney);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hideOpenRedDialog() {
        try {
            if (redOpenDialog != null && redOpenDialog.isShowing()) {
                redOpenDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showOpenRedSuccessDialog(String money) {
        StyledDialog.buildIosAlert("提示", "恭喜您获得" + money + "元现金", new MyDialogListener() {
            @Override
            public void onFirst() {
                onDismiss();
            }

            @Override
            public void onSecond() {

            }
        }).setBtnText("确定")
                .show();
    }
}
