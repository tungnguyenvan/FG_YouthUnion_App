package com.dev.nguyenvantung.fg_app.ui.main.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerHoatDongAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragment = new ArrayList<>();

    public ViewPagerHoatDongAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return listFragment.get(i);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    public void addFragment(Fragment fragment){
        listFragment.add(fragment);
    }
}
