package com.quduo.welfareshop.ui.mine.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.ReadActivity;
import com.quduo.welfareshop.db.BookList;
import com.quduo.welfareshop.mvp.BaseMainMvpFragment;
import com.quduo.welfareshop.ui.mine.presenter.MinePresenter;
import com.quduo.welfareshop.ui.mine.view.IMineView;
import com.quduo.welfareshop.ui.read.listener.OnSaveData2DBListener;
import com.quduo.welfareshop.util.FileUtils;
import com.quduo.welfareshop.util.ReaderUtil;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的主界面
 * Created by scene on 2018/1/25.
 */

public class MineFragment extends BaseMainMvpFragment<IMineView, MinePresenter> implements IMineView {
    Unbinder unbinder;

    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
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
    public MinePresenter initPresenter() {
        return new MinePresenter(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /*小说*/
    @OnClick(R.id.unlock_all)
    public void onClickRead() {
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
