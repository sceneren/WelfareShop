package com.quduo.welfareshop.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Author:scene
 * Time:2018/1/25  11:59
 * Description:嵌套ViewPager的adapter
 */
public class BaseViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mTab;
    private List<Fragment> fragmentList;

    public BaseViewPagerAdapter(FragmentManager fm, String[] mTab, List<Fragment> fragmentList) {
        super(fm);
        this.mTab = mTab;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mTab.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTab[position];
    }
}
