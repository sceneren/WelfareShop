package com.quduo.welfareshop.base;

import com.blankj.utilcode.util.ToastUtils;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.squareup.leakcanary.RefWatcher;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 懒加载
 * Created by YoKeyword on 16/6/5.
 */
public abstract class BaseMainFragment extends SupportFragment {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    private boolean needShowDisCountDialog = true;

    private boolean hasShowDisCountDialog = false;

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            MyApplication.getInstance().exit();
        } else {
//            //没有充值过并且没显示过优惠充值的对话框
//            if (!MyApplication.getInstance().getUserInfo().isPayed() && !hasShowDisCountDialog) {
//                hasShowDisCountDialog = true;
//                //显示一个Dialog
//                Intent intent = new Intent(_mActivity, DiscountRechargeDialog.class);
//                startActivity(intent);
//            } else {
            TOUCH_TIME = System.currentTimeMillis();
            ToastUtils.showShort(R.string.press_again_exit);
//            }

        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getActivity() != null) {
            RefWatcher refWatcher = MyApplication.getRefWatcher(getActivity());
            refWatcher.watch(this);
        }
    }
}
