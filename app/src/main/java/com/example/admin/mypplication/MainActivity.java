package com.example.admin.mypplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.viewgroup_tab)
    ViewGroupTab viewgroupTab;
    List<Fragment> listFragment;
    List<String> listString;
    private static final int[] APPCOMPAT_CHECK_ATTRS = {
            android.support.v7.appcompat.R.attr.colorPrimary
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);
        ButterKnife.bind(this);
        initFragment();
    }

    private void initFragment() {
        listFragment = new ArrayList<>();
        listFragment.add(new BaseFragment());
        listFragment.add(new BaseFragment());
        listFragment.add(new BaseFragment());
        listFragment.add(new BaseFragment());
        listString = Arrays.asList(getResources().getStringArray(R.array.red_package_tab_item));
        viewgroupTab.setAdapter(getSupportFragmentManager(), listString, listFragment);


    }
}
