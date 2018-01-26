package com.quduo.welfareshop.base;

import com.blankj.utilcode.util.ToastUtils;
import com.quduo.welfareshop.MyApplication;
import com.quduo.welfareshop.R;
import com.quduo.welfareshop.util.NetTimeUtils;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 懒加载
 * Created by YoKeyword on 16/6/5.
 */
public abstract class BaseMainFragment extends SupportFragment {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

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
            TOUCH_TIME = NetTimeUtils.getWebsiteDatetime();
            ToastUtils.showShort(R.string.press_again_exit);
        }
        return true;
    }

}
