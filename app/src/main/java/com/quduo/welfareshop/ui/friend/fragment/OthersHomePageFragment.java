package com.quduo.welfareshop.ui.friend.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hss01248.dialog.StyledDialog;
import com.lzy.okgo.OkGo;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.activity.PreviewImageActivity;
import com.quduo.welfareshop.base.GlideApp;
import com.quduo.welfareshop.base.UnlockLisenter;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.DynamicCommentSuccessEvent;
import com.quduo.welfareshop.event.UpdateScoreAndDiamondEvent;
import com.quduo.welfareshop.http.api.ApiUtil;
import com.quduo.welfareshop.mvp.BaseBackMvpFragment;
import com.quduo.welfareshop.ui.friend.activity.ChatActivity;
import com.quduo.welfareshop.ui.friend.adapter.HomePageAlbumAdapter;
import com.quduo.welfareshop.ui.friend.adapter.HomePageDynamicAdapter;
import com.quduo.welfareshop.ui.friend.adapter.HomePageVideoAdapter;
import com.quduo.welfareshop.ui.friend.adapter.HomePageVideoChatUserAdapter;
import com.quduo.welfareshop.ui.friend.dialog.CommentDynamicDialog;
import com.quduo.welfareshop.ui.friend.entity.DynamicInfo;
import com.quduo.welfareshop.ui.friend.entity.FriendVideoDetailInfo;
import com.quduo.welfareshop.ui.friend.entity.HomePageInfo;
import com.quduo.welfareshop.ui.friend.entity.UserVideoInfo;
import com.quduo.welfareshop.ui.friend.entity.VideoChatUserInfo;
import com.quduo.welfareshop.ui.friend.presenter.OthersHomePagePresenter;
import com.quduo.welfareshop.ui.friend.view.IOthersHomePageView;
import com.quduo.welfareshop.ui.mine.activity.AlbumActivity;
import com.quduo.welfareshop.ui.mine.entity.MyUserDetailInfo;
import com.quduo.welfareshop.util.DialogUtils;
import com.quduo.welfareshop.util.DistanceUtil;
import com.quduo.welfareshop.util.WeakDataHolder;
import com.quduo.welfareshop.widgets.CustomGridView;
import com.quduo.welfareshop.widgets.RatioImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

public class OthersHomePageFragment extends BaseBackMvpFragment<IOthersHomePageView, OthersHomePagePresenter> implements IOthersHomePageView {
    private static final String ARG_ID = "user_id";
    private static final String ARG_DISTANCE = "distance";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.status_view)
    StatusViewLayout statusView;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.title_layout)
    AppBarLayout titleLayout;
    Unbinder unbinder;

    private RatioImageView coverImage;
    private ImageView avatar;
    private TextView nickname;
    private LinearLayout layoutSexAndAge;
    private ImageView sex;
    private TextView age;
    private LinearLayout layoutMedal;
    private TextView distance;
    private TextView popularity;
    private TextView fans;
    private TextView signature;
    private LinearLayout btnFollow;
    private ImageView btnFollowImage;
    private TextView btnFollowText;
    private CustomGridView albumGridview;
    private CustomGridView videoGridview;
    private TextView totalVideoVhatCount;
    private TextView videoChatPrice;
    private CustomGridView videoChatAvaterGridView;
    private TagFlowLayout tagLayout;


    private int othersUserId = 0;
    private int othersDistance = 0;

    private HomePageInfo homePageInfo;
    private List<DynamicInfo> list;
    private HomePageDynamicAdapter adapter;

    private HomePageAlbumAdapter albumAdapter;
    private List<String> albumList;
    private ArrayList<MyUserDetailInfo.PhotosBean> photosBeanList;

    private HomePageVideoAdapter videoAdapter;
    private List<UserVideoInfo> videoList;

    private HomePageVideoChatUserAdapter videoChatUserAdapter;
    private List<VideoChatUserInfo> videoChatUserInfoList;

    private TagAdapter<String> tagAdapter;
    private List<String> tagList;

    private LayoutInflater inflater;

    private int[] tagBgResId = {R.drawable.bg_tag_1, R.drawable.bg_tag_2, R.drawable.bg_tag_3, R.drawable.bg_tag_4, R.drawable.bg_tag_5};

    public static OthersHomePageFragment newInstance(int othersUserId, int distance) {
        Bundle args = new Bundle();
        args.putInt(ARG_ID, othersUserId);
        args.putInt(ARG_DISTANCE, distance);
        OthersHomePageFragment fragment = new OthersHomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (getArguments() != null) {
            othersUserId = getArguments().getInt(ARG_ID, 0);
            othersUserId = 1864;
            othersDistance = getArguments().getInt(ARG_DISTANCE, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_others_home_page, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void initToolbar() {
        toolbarTitle.setText("");
        initToolbarNav(toolbar);
    }

    @Override
    public void initView() {
        inflater = LayoutInflater.from(getContext());
        initRefreshLayout();
        initRecyclerView();
        initHeaderView();
        presenter.getData(true, othersDistance, othersUserId);
    }

    private void initRefreshLayout() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                presenter.getData(false, othersDistance, othersUserId);
            }
        });
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new HomePageDynamicAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                try {
                    int scrollYDistance = getScollYDistance();
                    if (scrollYDistance > PtrLocalDisplay.SCREEN_WIDTH_PIXELS) {
                        titleLayout.setVisibility(View.VISIBLE);
                        titleLayout.setBackgroundResource(R.color.theme_color);
                    } else {
                        if (scrollYDistance < SizeUtils.dp2px(40)) {
                            titleLayout.setVisibility(View.GONE);
                        } else {
                            float scollPrecent = (float) scrollYDistance / (float) PtrLocalDisplay.SCREEN_WIDTH_PIXELS;
                            int alpha = (int) (255 * scollPrecent);
                            int bgColor = Color.argb(alpha, 255, 142, 175);
                            titleLayout.setVisibility(View.VISIBLE);
                            titleLayout.setBackgroundColor(bgColor);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    if (view.getId() == R.id.show_all) {
                        if (list.get(position).isShowAll()) {
                            //当前是展开 需要关闭
                            list.get(position).setShowAll(false);
                        } else {
                            list.get(position).setShowAll(true);
                        }
                        adapter.notifyItemChanged(position + adapter.getHeaderLayoutCount());
                    } else if (view.getId() == R.id.video_layout) {
                        if (list.get(position).isPayed()) {
                            DynamicInfo dynamicInfo = list.get(position);
                            FriendVideoDetailInfo detailInfo = new FriendVideoDetailInfo();
                            detailInfo.setId(dynamicInfo.getId());
                            detailInfo.setAvatar(dynamicInfo.getAvatar());
                            detailInfo.setContent(dynamicInfo.getContent());
                            detailInfo.setNickName(dynamicInfo.getNickname());
                            detailInfo.setPlay_times(dynamicInfo.getPlay_times());
                            detailInfo.setThumb(dynamicInfo.getThumb());
                            detailInfo.setUserId(dynamicInfo.getUser_id());
                            detailInfo.setVideo_url(dynamicInfo.getUrl());
                            detailInfo.setPrice(dynamicInfo.getPrice());
                            detailInfo.setPayed(dynamicInfo.isPayed());
                            detailInfo.setPosition(position);
                            start(FriendVideoDetailFragment.newInstance(detailInfo, FriendVideoDetailFragment.FROM_INTERACT));
                        } else {
                            showNeedUnlockDialog(position);
                        }
                    } else if (view.getId() == R.id.btn_good) {
                        if (!list.get(position).isIs_good()) {
                            presenter.zanDynamic(position, list.get(position).getId());
                        }
                    } else if (view.getId() == R.id.btn_comment) {
                        Intent intent = new Intent(_mActivity, CommentDynamicDialog.class);
                        intent.putExtra(CommentDynamicDialog.ARG_DYNAMIC_ID, list.get(position).getId());
                        intent.putExtra(CommentDynamicDialog.ARG_DYNAMIC_POSITION, position);
                        startActivity(intent);
                    } else if (view.getId() == R.id.image) {
                        toPreviewImageActivity(list.get(position).getImages());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_friend_others_home_page_header, (ViewGroup) recyclerView.getParent(), false);
        adapter.addHeaderView(headerView);
        coverImage = headerView.findViewById(R.id.cover_image);
        ImageView topBack = headerView.findViewById(R.id.top_back);
        avatar = headerView.findViewById(R.id.avatar);
        nickname = headerView.findViewById(R.id.nickname);
        layoutSexAndAge = headerView.findViewById(R.id.layout_sex_and_age);
        sex = headerView.findViewById(R.id.sex);
        age = headerView.findViewById(R.id.age);
        layoutMedal = headerView.findViewById(R.id.layout_medal);
        distance = headerView.findViewById(R.id.distance);
        popularity = headerView.findViewById(R.id.popularity);
        fans = headerView.findViewById(R.id.fans);
        signature = headerView.findViewById(R.id.signature);
        btnFollow = headerView.findViewById(R.id.btn_follow);
        btnFollowImage = headerView.findViewById(R.id.btn_follow_image);
        btnFollowText = headerView.findViewById(R.id.btn_follow_text);
        albumGridview = headerView.findViewById(R.id.album_gridview);
        videoGridview = headerView.findViewById(R.id.video_gridview);
        totalVideoVhatCount = headerView.findViewById(R.id.total_video_chat_count);
        videoChatPrice = headerView.findViewById(R.id.video_chat_price);
        videoChatAvaterGridView = headerView.findViewById(R.id.videoChatAvaterGridView);
        tagLayout = headerView.findViewById(R.id.tag_layout);

        topBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (homePageInfo.getSubscribe_id() == 0) {
                        presenter.followUser(homePageInfo.getId());
                    } else {
                        presenter.cancelFollowUser(homePageInfo.getSubscribe_id());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public OthersHomePagePresenter initPresenter() {
        return new OthersHomePagePresenter(this);
    }

    @Override
    public void showLoadingPage() {
        try {
            layoutBottom.setVisibility(View.GONE);
            statusView.showLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showContentPage() {
        try {
            layoutBottom.setVisibility(View.VISIBLE);
            statusView.showContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showErrorPage() {
        try {
            layoutBottom.setVisibility(View.GONE);
            statusView.showFailed(retryListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.getData(true, othersDistance, othersUserId);
        }
    };

    @Override
    public void bindData(HomePageInfo data) {
        try {
            this.homePageInfo = data;
            list.clear();
            list.addAll(data.getDynamic());
            adapter.notifyDataSetChanged();
            //绑定header
            GlideApp.with(this)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + homePageInfo.getCover())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(coverImage);
            GlideApp.with(this)
                    .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + homePageInfo.getAvatar())
                    .circleCrop()
                    .placeholder(R.drawable.ic_default_avatar)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(avatar);
            nickname.setText(homePageInfo.getNickname());
            layoutSexAndAge.setBackgroundResource(homePageInfo.getSex() == 1 ? R.drawable.bg_sex_male : R.drawable.bg_sex_female);
            sex.setImageResource(homePageInfo.getSex() == 1 ? R.drawable.ic_near_sex_male_white : R.drawable.ic_near_sex_female_white);
            age.setText(MessageFormat.format("{0}岁", homePageInfo.getAge()));

            //勋章
            layoutMedal.removeAllViews();
            for (String medal : homePageInfo.getMedal()) {
                ImageView imageView = new ImageView(getContext());
                ViewGroup.MarginLayoutParams params = new LinearLayout.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.height = SizeUtils.dp2px(15);
                params.width = SizeUtils.dp2px(15);
                params.setMarginStart(SizeUtils.dp2px(5));
                imageView.setLayoutParams(params);
                GlideApp.with(this)
                        .load(MyApplication.getInstance().getConfigInfo().getFile_domain() + medal)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);
                layoutMedal.addView(imageView);
            }

            distance.setText(DistanceUtil.formatDistance(homePageInfo.getDistance()));
            popularity.setText(String.valueOf(homePageInfo.getView_times()));
            fans.setText(String.valueOf(homePageInfo.getSubscribe()));
            signature.setText(homePageInfo.getSignature());
            bindFollowState();
            if (albumList == null) {
                albumList = new ArrayList<>();
                albumAdapter = new HomePageAlbumAdapter(getContext(), albumList);
                albumGridview.setAdapter(albumAdapter);
                albumGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        toAlbumActivity();
                    }
                });
            } else {
                albumList.clear();
            }
            albumList.addAll(homePageInfo.getPhotos());
            albumAdapter.notifyDataSetChanged();

            if (videoList == null) {
                videoList = new ArrayList<>();
                videoAdapter = new HomePageVideoAdapter(getContext(), videoList);
                videoGridview.setAdapter(videoAdapter);
                videoGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        WeakDataHolder.getInstance().saveData(UserAllVideoFragment.ARG_ALL_VIDEO + homePageInfo.getId(), homePageInfo.getVideos());
                        start(UserAllVideoFragment.newInstance(homePageInfo.getId()));
                    }
                });
            } else {
                videoList.clear();
            }
            videoList.addAll(homePageInfo.getVideos());
            videoAdapter.notifyDataSetChanged();

            totalVideoVhatCount.setText(MessageFormat.format("最近{0}位用户和TA视频过", homePageInfo.getVideo_times()));
            videoChatPrice.setText(MessageFormat.format("{0}积分/分钟", MyApplication.getInstance().getUserInfo().getChat_price()));

            if (videoChatUserInfoList == null) {
                videoChatUserInfoList = new ArrayList<>();
                videoChatUserAdapter = new HomePageVideoChatUserAdapter(getContext(), videoChatUserInfoList);
                videoChatAvaterGridView.setAdapter(videoChatUserAdapter);
            } else {
                videoChatUserInfoList.clear();
            }
            videoChatUserInfoList.addAll(homePageInfo.getChat_users());
            videoChatUserAdapter.notifyDataSetChanged();

            if (tagList == null) {
                tagList = new ArrayList<>();
                tagAdapter = new TagAdapter<String>(tagList) {
                    @Override
                    public View getView(FlowLayout parent, int position, String item) {
                        TextView tv = inflater.inflate(R.layout.fragment_friend_others_home_page_tag_item, tagLayout, false).findViewById(R.id.tag);
                        tv.setText(item);
                        tv.setBackgroundResource(tagBgResId[new Random().nextInt(5)]);
                        return tv;
                    }
                };
                tagLayout.setAdapter(tagAdapter);
            } else {
                tagList.clear();
            }
            tagList.addAll(homePageInfo.getTags());
            tagAdapter.notifyDataChanged();

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
    public void alert(String message) {
        try {
            StyledDialog.buildIosAlert("提示", message, null)
                    .setActivity(_mActivity)
                    .setBtnText("确定")
                    .show();
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
    public void zanSuccess(int position) {
        try {
            list.get(position).setIs_good(true);
            list.get(position).setGood(list.get(position).getGood() + 1);
            adapter.notifyItemChanged(position + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void followSuccess(int id) {
        try {
            homePageInfo.setSubscribe_id(id);
            bindFollowState();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelFollowSuccess() {
        try {
            homePageInfo.setSubscribe_id(0);
            bindFollowState();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unlockSuccess(int position, int currentScore) {
        try {
            MyApplication.getInstance().getUserInfo().setScore(currentScore);
            EventBus.getDefault().post(new UpdateScoreAndDiamondEvent());
            list.get(position).setPayed(true);
            adapter.notifyItemChanged(position + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unlockChatSuccess(int currentScore) {
        try {
            MyApplication.getInstance().getUserInfo().setUnlock_chat(1);
            MyApplication.getInstance().getUserInfo().setScore(currentScore);
            EventBus.getDefault().post(new UpdateScoreAndDiamondEvent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindFollowState() {
        if (homePageInfo.getSubscribe_id() == 0) {
            //未关注
            btnFollow.setBackgroundResource(R.drawable.btn_home_page_follow);
            btnFollowImage.setVisibility(View.VISIBLE);
            btnFollowText.setText("关注");
        } else {
            //已关注
            btnFollow.setBackgroundResource(R.drawable.bg_home_page_has_follow);
            btnFollowImage.setVisibility(View.GONE);
            btnFollowText.setText("已关注");
        }
    }

    private void toAlbumActivity() {
        Intent intent = new Intent(_mActivity, AlbumActivity.class);
        intent.putExtra(AlbumActivity.ARG_IS_MINE, false);
        if (photosBeanList == null) {
            photosBeanList = new ArrayList<>();
        }
        photosBeanList.clear();
        for (String str : albumList) {
            MyUserDetailInfo.PhotosBean bean = new MyUserDetailInfo.PhotosBean();
            bean.setUrl(str);
            photosBeanList.add(bean);
        }
        intent.putExtra(AlbumActivity.ARG_IMAGES, photosBeanList);
        startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

    @Override
    public void onDestroyView() {
        OkGo.getInstance().cancelTag(ApiUtil.OTHERS_HOME_PAGE_TAG);
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }

    public int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

    @Subscribe
    public void sendCommentSuccess(DynamicCommentSuccessEvent event) {
        if (event != null) {
            list.get(event.getPosition()).getComments().add(0, event.getCommentInfo());
            adapter.notifyItemChanged(event.getPosition() + 1);
        }
    }

    private void showNeedUnlockDialog(final int position) {
        try {
            DialogUtils.getInstance().showNeedUnlockDialog(_mActivity, list.get(position).getPrice(), MyApplication.getInstance().getUserInfo().getScore(), AppConfig.POSITION_FRIEND_VIDEO_DETAIL, new UnlockLisenter() {
                @Override
                public void unlock() {
                    presenter.unlockVideo(position, list.get(position).getId());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toPreviewImageActivity(String url) {
        ArrayList<String> previewImageUrls = new ArrayList<>();
        previewImageUrls.add(MyApplication.getInstance().getConfigInfo().getFile_domain() + url);
        Intent intent = new Intent(_mActivity, PreviewImageActivity.class);
        intent.putExtra(PreviewImageActivity.ARG_URLS, previewImageUrls);
        intent.putExtra(PreviewImageActivity.ARG_POSITION, 0);
        _mActivity.startActivity(intent);
    }

    @OnClick(R.id.send_message)
    public void onClickSendMessage() {
        if (MyApplication.getInstance().getUserInfo().getUnlock_chat() != 0) {
            toChatMessage();
        } else {
            DialogUtils.getInstance().showUnlockChatDialog(_mActivity, AppConfig.POSITION_FRIEND_OTHERS_INFO, new UnlockLisenter() {
                @Override
                public void unlock() {
                    presenter.unlockChat();
                }
            });
        }
    }

    @OnClick(R.id.video_chat)
    public void onClickVideoChat() {
        try {
            if (MyApplication.getInstance().getUserInfo().getScore() > MyApplication.getInstance().getUserInfo().getChat_price()) {
                DialogUtils.getInstance().showVideoChatNoticeDialog(_mActivity, homePageInfo.getAvatar(), homePageInfo.getNickname());
            } else {
                DialogUtils.getInstance().showVideoChatScoreNoEnough(_mActivity, AppConfig.POSITION_FRIEND_OTHERS_INFO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toChatMessage() {
        Intent intent = new Intent(_mActivity, ChatActivity.class);
        intent.putExtra("ID", String.valueOf(homePageInfo.getId()));
        intent.putExtra("NICKNAME", homePageInfo.getNickname());
        intent.putExtra("IS_FOLLOW", homePageInfo.getSubscribe_id() != 0);
        intent.putExtra("NEARBY", false);
        intent.putExtra("OTHERAVATAR", homePageInfo.getAvatar());
        startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }


}
