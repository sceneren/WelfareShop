package com.quduo.welfareshop.ui.mine.view;

import com.quduo.welfareshop.mvp.BaseView;
import com.quduo.welfareshop.ui.welfare.entity.WelfareGalleryInfo;

import java.util.List;

/**
 * Author:scene
 * Time:2018/3/1 11:10
 * Description:收藏的图片
 */

public interface IMyFollowImageView extends BaseView {

    void showMessage(String message);

    void bindData(List<WelfareGalleryInfo> data);

    void refreshFinish();

    void showLoadingDialog();

    void hideLoadingDialog();

    void cancelFollowSuccess(int position);

}
