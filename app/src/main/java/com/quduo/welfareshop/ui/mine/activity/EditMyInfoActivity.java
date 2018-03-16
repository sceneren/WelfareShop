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
import com.quduo.welfareshop.event.UpdateMyInfoSuccessEvent;
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

    private EditInfoPhotoAdapter adapter;
    private List<MyUserDetailInfo.PhotosBean> list = new ArrayList<>();
    private MyUserDetailInfo detailUserInfo;

    private List<String> sexList = null;
    private List<String> emotionList = null;
    private List<String> bloodList = null;
    private List<String> heightList = null;
    private List<String> weightList = null;


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
            blood.setText(detailUserInfo.getBlood_type());
        }
        if (!StringUtils.isEmpty(detailUserInfo.getHeight()) && !detailUserInfo.getHeight().equals("0.00")) {
            height.setText(detailUserInfo.getHeight());
        }
        if (!StringUtils.isEmpty(detailUserInfo.getWeight()) && !detailUserInfo.getWeight().equals("0.00")) {
            weight.setText(detailUserInfo.getWeight());
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

    @OnClick(R.id.toolbar_text)
    public void onClickSave() {
        presenter.updateMyInfo();
    }

    @OnClick(R.id.layout_avatar)
    public void onClickAvatar() {
        ImageSelectorUtil.openImageList(EditMyInfoActivity.this, 1, REQUEST_AVATAR_CODE);
    }

    @OnClick(R.id.nickname)
    public void onClick() {
        toEditSingleActivity("昵称", nickname.getText().toString().trim());
    }

    @OnClick(R.id.sex)
    public void onClickSex() {
        String sexStr = sex.getText().toString().trim();
        chooseSex(sexStr);
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

    @OnClick(R.id.des)
    public void onClickDes() {
        toEditSingleActivity("签名", des.getText().toString().trim());
    }

    @OnClick(R.id.emotion)
    public void onClickEmotion() {
        String emotionStr = emotion.getText().toString().trim();
        chooseEmotion(emotionStr);
    }

    @OnClick(R.id.job)
    public void onClickJob() {
        toEditSingleActivity("职业", job.getText().toString().trim());
    }

    @OnClick(R.id.blood)
    public void onClickBlood() {
        String bloodStr = blood.getText().toString().trim();
        chooseBlood(bloodStr);
    }

    @OnClick(R.id.height)
    public void onClickHeight() {
        String heightStr = height.getText().toString().trim();
        chooseHeight(heightStr);
    }

    @OnClick(R.id.weight)
    public void onClickWeight() {
        String weightStr = weight.getText().toString().trim();
        chooseWeight(weightStr);
    }

    @OnClick(R.id.wechat_num)
    public void onClickWeChatNum() {
        toEditSingleActivity("微信", wechatNum.getText().toString().trim());
    }

    @OnClick(R.id.phone_num)
    public void onClickPhoneNum() {
        toEditSingleActivity("电话", phoneNum.getText().toString().trim());
    }

    @Subscribe
    public void onUpdateSuccess(EditMyInfoEvent event) {
        if (event == null) {
            return;
        }
        if (event.getTitle().equals("昵称")) {
            nickname.setText(event.getContent());
        } else if (event.getTitle().equals("签名")) {
            des.setText(event.getContent());
        } else if (event.getTitle().equals("职业")) {
            job.setText(event.getContent());
        } else if (event.getTitle().equals("微信")) {
            wechatNum.setText(event.getContent());
        } else if (event.getTitle().equals("电话")) {
            phoneNum.setText(event.getContent());
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

    //选择情感
    private void chooseEmotion(final String emotionStr) {
        if (emotionList == null) {
            emotionList = new ArrayList<>();
            emotionList.add("未知");
            emotionList.add("未婚");
            emotionList.add("已婚");
            emotionList.add("离异");
        }
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(EditMyInfoActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                emotion.setText(emotionList.get(options1));
            }
        }).build();
        optionsPickerView.setPicker(emotionList);
        switch (emotionStr) {
            case "未婚":
                optionsPickerView.setSelectOptions(1);
                break;
            case "已婚":
                optionsPickerView.setSelectOptions(2);
                break;
            case "离异":
                optionsPickerView.setSelectOptions(3);
                break;
            default:
                optionsPickerView.setSelectOptions(0);
                break;
        }
        optionsPickerView.show();
    }

    //选择血型
    private void chooseBlood(String bloodStr) {
        if (bloodList == null) {
            bloodList = new ArrayList<>();
            bloodList.add("未知");
            bloodList.add("A型");
            bloodList.add("B型");
            bloodList.add("O型");
            bloodList.add("AB型");
        }
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(EditMyInfoActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                blood.setText(bloodList.get(options1));
            }
        }).build();
        optionsPickerView.setPicker(bloodList);
        switch (bloodStr) {
            case "A型":
                optionsPickerView.setSelectOptions(1);
                break;
            case "B型":
                optionsPickerView.setSelectOptions(2);
                break;
            case "O型":
                optionsPickerView.setSelectOptions(3);
                break;
            case "AB型":
                optionsPickerView.setSelectOptions(4);
                break;
            default:
                optionsPickerView.setSelectOptions(0);
                break;
        }
        optionsPickerView.show();
    }

    //选择身高
    private void chooseHeight(String heightStr) {
        if (heightList == null) {
            heightList = new ArrayList<>();
            for (int i = 70; i <= 230; i++) {
                heightList.add(String.valueOf(i) + "CM");
            }
        }
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(EditMyInfoActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                height.setText(heightList.get(options1));
            }
        }).build();
        optionsPickerView.setPicker(heightList);
        int size = heightList.size();
        for (int i = 0; i < size; i++) {
            if (heightStr.equals((heightList.get(i)))) {
                optionsPickerView.setSelectOptions(i);
            }
        }
        optionsPickerView.show();
    }

    //选择体重
    private void chooseWeight(String weightStr) {
        if (weightList == null) {
            weightList = new ArrayList<>();
            for (int i = 20; i <= 200; i++) {
                weightList.add(String.valueOf(i) + "KG");
            }
        }
        OptionsPickerView optionsPickerView = new OptionsPickerView.Builder(EditMyInfoActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                weight.setText(weightList.get(options1));
            }
        }).build();
        optionsPickerView.setPicker(weightList);
        int size = weightList.size();
        for (int i = 0; i < size; i++) {
            if (weightStr.equals((weightList.get(i)))) {
                optionsPickerView.setSelectOptions(i);
            }
        }
        optionsPickerView.show();
    }


    //选择生日
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

    @Override
    public void updateMyInfoSuccess() {
        try {
            MyApplication.getInstance().getUserInfo().setNickname(getNickName());
            EventBus.getDefault().post(new UpdateMyInfoSuccessEvent());
            EditMyInfoActivity.this.onBackPressedSupport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getNickName() {
        return nickname.getText().toString().trim();
    }

    @Override
    public int getSex() {
        String sexStr = sex.getText().toString().trim();
        switch (sexStr) {
            case "男":
                return 1;
            case "女":
                return 2;
            default:
                return 0;
        }
    }

    @Override
    public long getBirthday() {
        String strBirthday = birthday.getText().toString().trim();
        DateTime dateTime = new DateTime(strBirthday);
        return dateTime.getMillis() / 1000;
    }

    @Override
    public String getDes() {
        return des.getText().toString().trim();
    }

    @Override
    public String getEmotion() {
        return emotion.getText().toString().trim();
    }

    @Override
    public String getJob() {
        return job.getText().toString();
    }

    @Override
    public String getBloodType() {
        return blood.getText().toString();
    }

    @Override
    public String getMyHeight() {
        return height.getText().toString();
    }

    @Override
    public String getMyWeight() {
        return weight.getText().toString();
    }

    @Override
    public String getWechatNum() {
        return wechatNum.getText().toString().trim();
    }

    @Override
    public String getPhoneNum() {
        return phoneNum.getText().toString().trim();
    }

    private void toEditSingleActivity(String title, String content) {
        Intent intent = new Intent(EditMyInfoActivity.this, EditSingleActivity.class);
        intent.putExtra(EditSingleActivity.ARG_TITLE, title);
        intent.putExtra(EditSingleActivity.ARG_CONTENT, content);
        startActivity(intent);
    }
}
