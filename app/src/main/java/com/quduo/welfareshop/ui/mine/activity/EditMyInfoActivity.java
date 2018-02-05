package com.quduo.welfareshop.ui.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.event.EditMyInfoEvent;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.mine.adapter.EditInfoPhotoAdapter;
import com.quduo.welfareshop.ui.mine.presenter.EditMyInfoPresenter;
import com.quduo.welfareshop.ui.mine.view.IEditMyInfoView;
import com.quduo.welfareshop.widgets.CircleImageView;
import com.quduo.welfareshop.widgets.CustomeGridView;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig;

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
 * Time:2018/2/2 16:38
 * Description:编辑资料
 */
public class EditMyInfoActivity extends BaseMvpActivity<IEditMyInfoView, EditMyInfoPresenter> implements IEditMyInfoView {
    private static final int REQUEST_LIST_CODE = 10001;
    private static final int REQUEST_CAMERA_CODE = 10002;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.photoGridView)
    CustomeGridView photoGridView;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.birthday)
    TextView birthday;
    @BindView(R.id.des)
    TextView des;
    @BindView(R.id.emotion)
    TextView emotion;
    @BindView(R.id.job)
    TextView job;
    @BindView(R.id.blood)
    TextView blood;
    @BindView(R.id.height)
    TextView height;
    @BindView(R.id.weight)
    TextView weight;
    @BindView(R.id.wechat_num)
    TextView wechatNum;
    @BindView(R.id.phone_num)
    TextView phoneNum;
    Unbinder unbinder;

    private List<String> list = new ArrayList<>();
    private EditInfoPhotoAdapter adapter;

    private boolean hasInitChoosePhoto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_edit_my_info);
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initToolbar();
        initView();
    }

    private void initToolbar() {
        toolbarTitle.setText("编辑");
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void initView() {
        adapter = new EditInfoPhotoAdapter(EditMyInfoActivity.this, list);
        photoGridView.setAdapter(adapter);
        photoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.size() < 9) {
                    if (position == 0) {
                        //添加图片
                        if (!hasInitChoosePhoto) {
                            initPhotoImageLoader();
                        }
                        openImageList(list.size());
                    } else {
                        //删除图片
                        list.remove(position - 1);
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    //删除图片
                    list.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    // 自定义图片加载器
    private void initPhotoImageLoader() {
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                GlideApp.with(context).load(path).into(imageView);
            }
        });
        hasInitChoosePhoto = true;
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
    public EditMyInfoPresenter initPresenter() {
        return new EditMyInfoPresenter(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK && data != null) {
            String path = data.getStringExtra("result"); // 图片地址
            list.add(path);
            adapter.notifyDataSetChanged();
        }
        // 图片选择结果回调
        if (requestCode == REQUEST_LIST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra("result");
            for (String path : pathList) {
                list.add(path);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void openImageList(int currentPhotoNum) {
        // 自由配置选项
        ISListConfig config = new ISListConfig.Builder()
                // 是否多选, 默认true
                .multiSelect(true)
                // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                .rememberSelected(false)
                // “确定”按钮背景色
                .btnBgColor(Color.TRANSPARENT)
                // “确定”按钮文字颜色
                .btnTextColor(Color.WHITE)
                // 使用沉浸式状态栏
                .statusBarColor(ContextCompat.getColor(EditMyInfoActivity.this, R.color.colorAccent))
                // 返回图标ResId
                .backResId(R.drawable.ic_toolbar_back)
                // 标题
                .title("图片")
                // 标题文字颜色
                .titleColor(Color.WHITE)
                // TitleBar背景色
                .titleBgColor(ContextCompat.getColor(EditMyInfoActivity.this, R.color.colorAccent))
//                // 裁剪大小。needCrop为true的时候配置
//                .cropSize(1, 1, 200, 200)
//                .needCrop(true)
                // 第一个是否显示相机，默认true
                .needCamera(true)
                // 最大选择图片数量，默认9
                .maxNum(9 - currentPhotoNum)
                .build();
        // 跳转到图片选择器
        ISNav.getInstance().toListActivity(this, config, REQUEST_LIST_CODE);
    }


    @OnClick(R.id.nickname)
    public void onClick() {
        Intent intent = new Intent(EditMyInfoActivity.this, EditSingleActivity.class);
        intent.putExtra(EditSingleActivity.ARG_TITLE, "昵称");
        startActivity(intent);
    }

    @Subscribe
    public void onUpdateSuccess(EditMyInfoEvent event) {
        if (event == null) {
            return;
        }
        if (event.getTitle().equals("昵称")) {
            nickname.setText(event.getContent());
        }
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        unbinder.unbind();
    }

}
