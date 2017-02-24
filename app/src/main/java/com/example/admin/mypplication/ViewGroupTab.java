package com.example.admin.mypplication;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Translation threw an uncaught SocketException. Disable Plugin
 * Created by zq on 2017/2/22.
 *
 * Analyze  APK
 */

public class ViewGroupTab extends LinearLayout {

    TabLayout redbagTab;

    public MyViewPager getRedbagViewpager() {
        return redbagViewpager;
    }


    MyViewPager redbagViewpager;

    public TabLayout getRedbagTab() {
        return redbagTab;
    }


    ProjectLsitAdapter myProjectLsitAdapter;

    public ViewGroupTab(Context context) {
        super(context);
        init();
    }

    public ViewGroupTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public ViewGroupTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewGroupTab(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_group_tab, this);
        redbagTab = (TabLayout) findViewById(R.id.redbag_tab);
        redbagViewpager = (MyViewPager) findViewById(R.id.redbag_viewpager);
    }


    public void setAdapter(FragmentManager fragmentManager, List<String> listString, List<Fragment> listFragment) {
        //设置Viewpager不可滑动
        redbagViewpager.setScrollble(false);
        myProjectLsitAdapter = new ProjectLsitAdapter(fragmentManager, listString, listFragment);
        redbagViewpager.setAdapter(myProjectLsitAdapter);
        //设置Tabs的显示模式 固定的 = MODE_FIXED  可滑动的 = MODE_SCROLLABLE
        redbagTab.setTabMode(TabLayout.MODE_FIXED);
        //Tabs和Viewpager关联
        redbagTab.setupWithViewPager(redbagViewpager);
        LinearLayout linearLayout = (LinearLayout) redbagTab.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(getContext(),
                R.drawable.dd));
    }

}
