package com.example.admin.mypplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by zq on 2017/2/22.
 */

public class ProjectLsitAdapter extends  FragmentStatePagerAdapter {

    private List<String> tabNames;
    private List<Fragment> fragments;
    public ProjectLsitAdapter(FragmentManager fm, List<String> tabNames, List<Fragment> fragments) {
        super(fm);
        this.tabNames = tabNames;
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
    /**
     *这个函数就是给TabLayout的Tab设定Title
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames.get(position);
    }
}
