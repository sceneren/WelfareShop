package com.quduo.welfareshop.ui.read;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.db.BookCatalogue;
import com.quduo.welfareshop.ui.read.adapter.CatalogueAdapter;
import com.quduo.welfareshop.util.PageFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 目录
 * Created by Administrator on 2016/8/31 0031.
 */
public class CatalogFragment extends SupportFragment {
    public static final String ARGUMENT = "argument";

    private PageFactory pageFactory;
    ArrayList<BookCatalogue> catalogueList = new ArrayList<>();

    @BindView(R.id.lv_catalogue)
    ListView lv_catalogue;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData();
        initListener();
    }

    private void initData() {
        pageFactory = PageFactory.getInstance();
        catalogueList.addAll(pageFactory.getDirectoryList());
        CatalogueAdapter catalogueAdapter = new CatalogueAdapter(getContext(), catalogueList);
        catalogueAdapter.setCharter(pageFactory.getCurrentCharter());
        lv_catalogue.setAdapter(catalogueAdapter);
        catalogueAdapter.notifyDataSetChanged();
        try {
            lv_catalogue.setSelection(pageFactory.getCurrentCharter());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initListener() {
        lv_catalogue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pageFactory.changeChapter(catalogueList.get(position).getBookCatalogueStartPos());
                getActivity().finish();
            }
        });
    }

    /**
     * 用于从Activity传递数据到Fragment
     *
     * @param bookpath
     * @return
     */
    public static CatalogFragment newInstance(String bookpath) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, bookpath);
        CatalogFragment catalogFragment = new CatalogFragment();
        catalogFragment.setArguments(bundle);
        return catalogFragment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
