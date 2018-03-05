package com.quduo.welfareshop.ui.welfare.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.ReadActivity;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.db.BookList;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.read.listener.OnSaveData2DBListener;
import com.quduo.welfareshop.ui.welfare.adapter.NovelDetailAdapter;
import com.quduo.welfareshop.ui.welfare.presenter.NovelDetailPresenter;
import com.quduo.welfareshop.ui.welfare.view.INovelDetailView;
import com.quduo.welfareshop.util.FileUtils;
import com.quduo.welfareshop.util.ReaderUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * Author:scene
 * Time:2018/2/26 16:31
 * Description:小说详情
 */

public class NovelDetailFragment extends BaseBackMvpFragment<INovelDetailView, NovelDetailPresenter> implements INovelDetailView {
    private static final String ARG_NOVEL_ID = "ARG_NOVEL_ID";
    @BindView(R.id.toolbar_layout)
    AppBarLayout toolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.read_now)
    TextView readNow;
    @BindView(R.id.follow)
    TextView follow;
    Unbinder unbinder;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;

    private ImageView coverImage;

    private int novelId;
    private List<String> list;
    private NovelDetailAdapter adapter;

    public static NovelDetailFragment newInstance(int novelId) {
        Bundle args = new Bundle();
        args.putInt(ARG_NOVEL_ID, novelId);
        NovelDetailFragment fragment = new NovelDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            novelId = getArguments().getInt(ARG_NOVEL_ID, 0);
        }
        if (novelId == 0) {
            _mActivity.onBackPressed();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare_novel_detail, container, false);
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

        }
    };

    @Override
    public void initToolbar() {
        toolbarTitle.setText("");
        initToolbarNav(toolbar);
        toolbarLayout.setVisibility(View.GONE);
    }

    @Override
    public void initView() {
        showContentPage();

        initRecyclerView();
        initHeaderView();
    }

    private void initRecyclerView() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
            }
        });

        toolbarLayout.setVisibility(View.GONE);
        list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        adapter = new NovelDetailAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_welfare_novel_detail_header, null);
        coverImage = headerView.findViewById(R.id.cover_image);

        headerView.findViewById(R.id.title_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        adapter.addHeaderView(headerView);

        coverImage.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT >= 16) {
                            coverImage.getViewTreeObserver()
                                    .removeOnGlobalLayoutListener(this);
                        } else {
                            coverImage.getViewTreeObserver()
                                    .removeGlobalOnLayoutListener(this);
                        }
                        int height = coverImage.getHeight(); // 获取高度
                        LogUtils.e(height);
                        ViewGroup.LayoutParams layoutParams = coverImage.getLayoutParams();
                        layoutParams.width = (int) (243f * height / 325f);
                        coverImage.setLayoutParams(layoutParams);
                        String url = "http://img06.tooopen.com/images/20161214/tooopen_sy_190503622531.jpg";
                        GlideApp.with(NovelDetailFragment.this)
                                .asBitmap()
                                .centerCrop()
                                .load(url)
                                .into(coverImage);
                    }
                });


    }

    @Override
    public NovelDetailPresenter initPresenter() {
        return new NovelDetailPresenter(this);
    }

    @OnClick(R.id.read_now)
    public void onClickReadNow() {
        onClickRead();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void onClickRead() {
        List<BookList> allBooks = DataSupport.findAll(BookList.class);
        if (allBooks.size() > 0) {
            final BookList bookList = allBooks.get(0);
            bookList.setId(allBooks.get(0).getId());
            final String path = bookList.getBookpath();
            File file = new File(path);
            if (!file.exists()) {
                new AlertDialog.Builder(_mActivity)
                        .setTitle(getString(R.string.app_name))
                        .setMessage(path + "文件不存在,是否删除该书本？")
                        .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //数据库删除书籍
                                DataSupport.deleteAll(BookList.class, "bookpath = ?", path);

                            }
                        }).setCancelable(true).show();
                return;
            }
            ReadActivity.openBook(bookList, _mActivity);
        } else {
            BookList bookList = new BookList();
            final String bookName = FileUtils.getFileName(Environment.getExternalStorageDirectory().getPath() + File.separator + "396993.txt");
            bookList.setBookname(bookName);
            bookList.setBookpath(Environment.getExternalStorageDirectory().getPath() + File.separator + "396993.txt");
            ReaderUtil.addBook2DB(bookList, new OnSaveData2DBListener() {
                @Override
                public void onSaveSuccess() {
                    ToastUtils.showShort("导入书本成功");
                }

                @Override
                public void onSaveFail() {
                    ToastUtils.showShort("由于一些原因添加书本失败");
                }

                @Override
                public void onSaveRepeat() {
                    ToastUtils.showShort("书本" + bookName + "重复了");
                }
            });
        }

    }
}
