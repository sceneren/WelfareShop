package com.quduo.welfareshop.ui.friend.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lhh.apst.library.CustomPagerSlidingTabStrip;
import com.lhh.apst.library.ViewHolder;
import com.quduo.welfareshop.R;

import java.util.List;


/**
 * Author:scene
 * Time:2018/2/1 16:14
 * Description:交友的pagerAdapter
 */

public class FriendPagerAdapter extends FragmentPagerAdapter implements CustomPagerSlidingTabStrip.CustomTabProvider {
    private LayoutInflater mInflater;
    private List<Fragment> fragmentList;
    private List<String> titleList;

    public FriendPagerAdapter(Context context, FragmentManager fm, List<Fragment> fragmentList, List<String> titleList) {
        super(fm);
        mInflater = LayoutInflater.from(context);
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public View getSelectTabView(int position, View convertView) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.tab_title_friend_select, null);
        }
        TextView tv = ViewHolder.get(convertView, R.id.tvTab);
        tv.setText(getPageTitle(position));
        return convertView;
    }

    @Override
    public View getDisSelectTabView(int position, View convertView) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.tab_title_friend_unselect, null);
        }

        TextView tv = ViewHolder.get(convertView, R.id.tvTab);

        tv.setText(getPageTitle(position));

        return convertView;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
