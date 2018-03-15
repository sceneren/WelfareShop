package com.quduo.welfareshop.ui.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.event.EditMyInfoEvent;
import com.quduo.welfareshop.event.UpdateAvatarEvent;
import com.quduo.welfareshop.event.UploadImageEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseMvpActivity;
import com.quduo.welfareshop.ui.mine.adapter.EditInfoPhotoAdapter;
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;
import com.quduo.welfareshop.ui.mine.entity.UploadAvatarResultInfo;
import com.quduo.welfareshop.ui.mine.presenter.EditMyInfoPresenter;
import com.quduo.welfareshop.ui.mine.view.IEditMyInfoView;
import com.quduo.welfareshop.util.ImageSelectorUtil;
import com.quduo.welfareshop.widgets.CircleImageView;
import com.quduo.welfareshop.widgets.CustomGridView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.DateTime;
import org.joda.time.Instant;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnCompressListener;
import me.shaohui.advancedluban.OnMultiCompressListener;

/**
 * Author:scene
 * Time:2018/2/2 16:38
 * Description:编辑资料
 */
public class EditMyInfoActivity extends BaseMvpActivity<IEditMyInfoView, EditMyInfoPresenter> implements IEditMyInfoView {
    private static final int REQUEST_LIST_CODE = 10001;
    private static final int REQUEST_AVATAR_CODE = 10002;
    //private static final int REQUEST_CAMERA_CODE = 10002;

    public static final String ARG_USER_INFO = "user_info";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_text)
    TextView toolbarText;
    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.photoGridView)
    CustomGridView photoGridView;
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

    private List<String> sexList = null;
    private EditInfoPhotoAdapter adapter;

    private List<MyUserDetailInfo.PhotosBean> list = new ArrayList<>();
    private MyUserDetailInfo detailUserInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_edit_my_info);
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        detailUserInfo = (MyUserDetailInfo) getIntent().getSerializableExtra(ARG_USER_INFO);
        initToolbar();
        initView();
    }

    private void initToolbar() {
        toolbarTitle.setText("编辑");
        toolbarText.setText("保存");
        toolbar.setNavigationIcon(R.drawable.ic_toolbar_back_black);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        GlideApp.with(this)
                .asBitmap()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_default_avatar)
                .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + detailUserInfo.getAvatar())
                .into(avatar);

        initPhotoGridView(detailUserInfo.getPhotos());
        nickname.setText(detailUserInfo.getNickname());

        if (detailUserInfo.getSex() == 1) {
            sex.setText("男");
        } else if (detailUserInfo.getSex() == 2) {
            sex.setText("女");
        } else {
            sex.setText("保密");
        }

        DateTime dateTime = new DateTime(detailUserInfo.getBirthday());
        birthday.setText(dateTime.toString("yyyy-MM-dd"));

        des.setText(detailUserInfo.getSignature());
        emotion.setText(detailUserInfo.getMarital());
        job.setText(detailUserInfo.getJob());
        if (!StringUtils.isEmpty(detailUserInfo.getBlood_type())) {
            blood.setText(MessageFormat.format("{0}型", detailUserInfo.getBlood_type()));
        }
        if (!StringUtils.isEmpty(detailUserInfo.getHeight()) && !detailUserInfo.getHeight().equals("0.00")) {
            height.setText(MessageFormat.format("{0}cm", detailUserInfo.getHeight()));
        }
        if (!StringUtils.isEmpty(detailUserInfo.getWeight()) && !detailUserInfo.getWeight().equals("0.00")) {
            weight.setText(MessageFormat.format("{0}kg", detailUserInfo.getWeight()));
        }
        wechatNum.setText(detailUserInfo.getWeixin());
        phoneNum.setText(detailUserInfo.getMobile());
    }

    private void initPhotoGridView(List<MyUserDetailInfo.PhotosBean> photos) {
        list.addAll(photos);
        adapter = new EditInfoPhotoAdapter(EditMyInfoActivity.this, list);
        photoGridView.setAdapter(adapter);
        photoGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (list.size() < 9) {
                    if (position == 0) {
                        ImageSelectorUtil.openImageList(EditMyInfoActivity.this, 9 - list.size(), REQUEST_LIST_CODE);
                    } else {
                        //删除图片
                        presenter.deletePhoto(position - 1, list.get(position - 1).getId());
                    }
                } else {
                    //删除图片
                    presenter.deletePhoto(position, list.get(position).getId());
                }
            }
        });
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

        if (resultCode == RESULT_OK && data != null) {
            // 图片选择结果回调
            if (requestCode == REQUEST_LIST_CODE) {
                List<String> pathList = data.getStringArrayListExtra("result");
                List<File> files = new ArrayList<>();
                for (String filePath : pathList) {
                    files.add(new File(filePath));
                }
                Luban.compress(EditMyInfoActivity.this, files)
                        .launch(new OnMultiCompressListener() {
                            @Override
                            public void onStart() {
                                showLoadingDialog();
                            }

                            @Override
                            public void onSuccess(List<File> fileList) {
                                presenter.uploadPhoto(fileList);
                            }

                            @Override
                            public void onError(Throwable e) {
                                hideLoadingDialog();
                                showMessage("图片上传失败请重试");
                            }
                        });


            } else if (requestCode == REQUEST_AVATAR_CODE) {
                List<String> avatarPaths = data.getStringArrayListExtra("result");
                Luban.compress(EditMyInfoActivity.this, new File(avatarPaths.get(0)))
                        .launch(new OnCompressListener() {
                            @Override
                            public void onStart() {
                                showLoadingDialog();
                            }

                            @Override
                            public void onSuccess(File file) {
                                presenter.uploadAvatar(file);
                            }

                            @Override
                            public void onError(Throwable e) {
                                hideLoadingDialog();
                                showMessage("头像上传失败请重试");
                            }
                        });
            }
        }

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
        OkGo.getInstance().cancelTag(ApiUtil.UPLOAD_PHOTO_TAG);
        OkGo.getInstance().cancelTag(ApiUtil.DELETE_PHOTO_TAG);
        OkGo.getInstance().cancelTag(ApiUtil.UPLOAD_AVATAR_TAG);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.sex)
    public void onClickSex() {
        String sexStr = sex.getText().toString().trim();
        chooseSex(sexStr);
    }

    private void chooseSex(String sexStr) {
        if (sexList == null) {
            sexList = new ArrayList<>();
            sexList.add("男");
            sexList.add("女");
            sexList.add("保密");
        }
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(EditMyInfoActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                sex.setText(sexList.get(options1));
            }
        }).build();
        optionsPickerView.setPicker(sexList);
        switch (sexStr) {
            case "男":
                optionsPickerView.setSelectOptions(0);
                break;
            case "女":
                optionsPickerView.setSelectOptions(1);
                break;
            default:
                optionsPickerView.setSelectOptions(2);
                break;
        }
        optionsPickerView.show();
    }

    @OnClick(R.id.birthday)
    public void onClickBirthDay() {
        String birthDayStr = birthday.getText().toString().trim();
        long birthDayMillis;
        if (StringUtils.isEmpty(birthDayStr)) {
            birthDayMillis = new Instant().getMillis();
        } else {
            birthDayMillis = new Instant(birthDayStr).getMillis();
        }
        chooseBirthDay(birthDayMillis);
    }

    private void chooseBirthDay(long birthDayMillis) {
        TimePickerView pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                //选中事件回调
                DateTime dateTime = new DateTime(date);
                birthday.setText(dateTime.toString("yyyy-MM-dd"));
            }
        })
                .setRange(1900, 2050)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "").build();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(birthDayMillis);
        pvTime.setDate(calendar);
        pvTime.show();
    }

    @Override
    public void showLoadingDialog() {
        try {
            hideLoadingDialog();
            StyledDialog.buildLoading().setActivity(EditMyInfoActivity.this).show();
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
    public void uploadAvaterSuccess(UploadAvatarResultInfo resultInfo) {
        try {
            GlideApp.with(EditMyInfoActivity.this)
                    .asBitmap()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + resultInfo.getAvatar())
                    .into(avatar);
            EventBus.getDefault().post(new UpdateAvatarEvent(resultInfo.getAvatar()));
        } catch (Exception e) {
            e.printStackTrace();
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
    public void uploadPhotoSuccess(List<MyUserDetailInfo.PhotosBean> data) {
        try {
            list.clear();
            list.addAll(data);
            adapter.notifyDataSetChanged();
            EventBus.getDefault().post(new UploadImageEvent(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePhotoSuccess(int position) {
        try {
            list.remove(position);
            adapter.notifyDataSetChanged();
            EventBus.getDefault().post(new UploadImageEvent(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.layout_avatar)
    public void onClickAvatar() {
        ImageSelectorUtil.openImageList(EditMyInfoActivity.this, 1, REQUEST_AVATAR_CODE);
    }

    @OnClick(R.id.toolbar_text)
    public void onClickSave() {

    }
}
