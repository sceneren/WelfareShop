package com.quduo.welfareshop.ui.friend.presenter;

import com.lzy.okgo.model.HttpParams;
import com.quduo.welfareshop.config.AppConfig;
import com.quduo.welfareshop.event.UpdateSessionEvent;
import com.quduo.welfareshop.http.listener.HttpResultListener;
import com.quduo.welfareshop.mvp.BasePresenter;
import com.quduo.welfareshop.ui.friend.entity.ChatMessageInfo;
import com.quduo.welfareshop.ui.friend.model.ChatModel;
import com.quduo.welfareshop.ui.friend.view.IChatView;
import com.quduo.welfareshop.ui.welfare.entity.FollowSuccessInfo;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Author:scene
 * Time:2018/2/5 16:40
 * Description:聊天
 */

public class ChatPresenter extends BasePresenter<IChatView> {
    private ChatModel model;

    public ChatPresenter(IChatView view) {
        super(view);
        this.model = new ChatModel();
    }

    public void getAllMessage(boolean isRefresh, boolean isFirstEnter) {
        try {
            List<ChatMessageInfo> list = model.getAllMessage(AppConfig.userId, this.mView.getOtherUserId());
            mView.refreshComplete();
            mView.updateRecyclerView(list);
            if (!isRefresh) {
                mView.moveToBottom(isFirstEnter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(ChatMessageInfo chatMessageInfo) {
        try {
            model.sendTextMessage(chatMessageInfo);
            EventBus.getDefault().post(new UpdateSessionEvent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void followUser() {
        try {
            mView.showLoadingDialog();
            HttpParams params = new HttpParams();
            params.put("target_user_id", mView.getOtherUserId());
            params.put("from_nearby", mView.getFromNearby());
            params.put("longitude", mView.getLongitude());
            params.put("latitude", mView.getLatitude());
            model.followUser(params, new HttpResultListener<FollowSuccessInfo>() {
                @Override
                public void onSuccess(FollowSuccessInfo data) {
                    try {
                        mView.followUserSuccess(data.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.showMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        mView.hideLoadingDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
