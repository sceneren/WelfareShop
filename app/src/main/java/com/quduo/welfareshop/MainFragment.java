package com.quduo.welfareshop;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.quduo.welfareshop.event.StartBrotherEvent;
import com.quduo.welfareshop.event.TabSelectedEvent;
import com.quduo.welfareshop.ui.friend.fragment.FriendFragment;
import com.quduo.welfareshop.ui.mine.fragment.MineFragment;
import com.quduo.welfareshop.ui.red.fragment.RedFragment;
import com.quduo.welfareshop.ui.shop.activity.GoodsDetailActivity;
import com.quduo.welfareshop.ui.shop.fragment.ShopFragment;
import com.quduo.welfareshop.ui.welfare.fragment.WelfareFragment;
import com.quduo.welfareshop.widgets.bottombar.BottomBar;
import com.quduo.welfareshop.widgets.bottombar.BottomBarTab;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jzvd.JZVideoPlayer;
import me.yokeyword.fragmentation.SupportFragment;
import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * 主Fragment 新建分支
 * Created by scene on 2018/1/25.
 */

public class MainFragment extends SupportFragment {
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;
    public static final int FIVE = 4;
    @BindView(R.id.fl_tab_container)
    FrameLayout flTabContainer;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    Unbinder unbinder;
    @BindView(R.id.image)
    ImageView image;

    private SupportFragment[] mFragments = new SupportFragment[5];
    private List<String> tabNames = new ArrayList<>();

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment firstFragment = findChildFragment(WelfareFragment.class);
        tabNames.add(getString(R.string.tab_welfare));
        tabNames.add(getString(R.string.tab_friend));
        tabNames.add(getString(R.string.tab_shop));
        tabNames.add(getString(R.string.tab_red));
        tabNames.add(getString(R.string.tab_mine));
        if (firstFragment == null) {
            mFragments[FIRST] = WelfareFragment.newInstance();
            mFragments[SECOND] = FriendFragment.newInstance();
            mFragments[THIRD] = ShopFragment.newInstance();
            mFragments[FOUR] = RedFragment.newInstance();
            mFragments[FIVE] = MineFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOUR],
                    mFragments[FIVE]);
        } else {
            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.findFragmentByTag自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(FriendFragment.class);
            mFragments[THIRD] = findChildFragment(ShopFragment.class);
            mFragments[FOUR] = findChildFragment(RedFragment.class);
            mFragments[FIVE] = findChildFragment(MineFragment.class);
        }
        initView();
    }

    private boolean isWork = true;

    private ObjectAnimator animatorStar;
    private ObjectAnimator animatorEnd;

    private void showStartMoveImage() {

        _mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (animatorStar == null) {
                    animatorStar = ObjectAnimator.ofFloat(image, "translationX", PtrLocalDisplay.SCREEN_WIDTH_PIXELS, 0, 0);
                    animatorStar.setTarget(image);
                    animatorStar.setDuration(3000);
                    animatorStar.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            image.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            image.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    showEndMoveImage();
                                }
                            }, 7000);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }
                if (bottomBar.getCurrentItemPosition() == 0 || bottomBar.getCurrentItemPosition() == 1) {
                    animatorStar.start();
                }

            }
        });
    }

    public void showEndMoveImage() {
        _mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (animatorEnd == null) {
                    animatorEnd = ObjectAnimator.ofFloat(image, "translationX", 0, -PtrLocalDisplay.SCREEN_WIDTH_PIXELS, -PtrLocalDisplay.SCREEN_WIDTH_PIXELS);
                    animatorEnd.setTarget(image);
                    animatorEnd.setDuration(3000);
                    animatorEnd.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            image.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {
                            image.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }
                if (bottomBar.getCurrentItemPosition() == 0 || bottomBar.getCurrentItemPosition() == 1 || image.getVisibility() == View.VISIBLE) {
                    animatorEnd.start();
                }

            }
        });
    }

    private Thread thread;

    private void startMoveImageThread() {
        if (thread == null) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isWork) {
                        SystemClock.sleep(40000);
                        showStartMoveImage();
                    }
                }
            });
        }
        thread.start();
    }

    private void initView() {
//        GlideApp.with(this)
////                .load(R.drawable.ic_move_car)
////                .diskCacheStrategy(DiskCacheStrategy.ALL)
////                .into(image);
        //startMoveImageThread();

        bottomBar
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_tab_welfare_d, R.drawable.ic_tab_welfare_s, tabNames.get(FIRST)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_tab_friend_d, R.drawable.ic_tab_friend_s, tabNames.get(SECOND)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_tab_shop_d, R.drawable.ic_tab_shop_s, tabNames.get(THIRD)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_tab_red_d, R.drawable.ic_tab_red_s, tabNames.get(FOUR)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_tab_mine_d, R.drawable.ic_tab_mine_s, tabNames.get(FIVE)));

        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, final int prePosition) {
                try {
                    showHideFragment(mFragments[position], mFragments[prePosition]);
                    if (position == 3) {
                        toGoodsDetailActivity(13);
                        bottomBar.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                bottomBar.setCurrentItem(prePosition);
                            }
                        },500);
                    }
                    if (position == 1 || position == 0) {
                        image.clearAnimation();
                        image.setVisibility(View.GONE);
                    }
                    JZVideoPlayer.releaseAllVideos();
                    JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
                    JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        //setUnReadCount();
    }

    @Subscribe
    public void startBrother(StartBrotherEvent event) {
        start(event.targetFragment);
    }

    @Subscribe
    public void toIndexPage(TabSelectedEvent event) {
        bottomBar.setCurrentItem(event.position);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.image)
    public void onClickImage() {
        try {
            image.clearAnimation();
            bottomBar.setCurrentItem(2);
            image.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    private void toGoodsDetailActivity(int goodsId) {
        Intent intent = new Intent(_mActivity, GoodsDetailActivity.class);
        intent.putExtra(GoodsDetailActivity.ARG_ID, goodsId);
        startActivity(intent);
        _mActivity.overridePendingTransition(R.anim.h_fragment_enter, R.anim.h_fragment_exit);
    }

}
