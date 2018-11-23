package com.dev.nguyenvantung.fg_app.ui.hoatdong;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.ui.hoatdong.fragment.adapter.ViewPagerHoatDongAdapter;
import com.dev.nguyenvantung.fg_app.ui.hoatdong.fragment.coming.HoatDongComingFragment;
import com.dev.nguyenvantung.fg_app.ui.hoatdong.fragment.finished.HoatDongFinishedFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HoatDongFragment extends Fragment {
    private static final String TAG = "HoatDongFragment";
    private View view;
    @BindView(R.id.viewpager)
    public ViewPager mViewpager;
    @BindView(R.id.hoatdong_tablayout)
    public TabLayout mTabLayout;
    private static HoatDongFragment instance;


    public HoatDongFragment() {
        // Required empty public constructor
    }

    public static HoatDongFragment getInstance(){
        if (instance == null){
            instance = new HoatDongFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hoat_dong, container, false);
        ButterKnife.bind(this, view);

        initView();

        return view;
    }

    private void initView() {
        ViewPagerHoatDongAdapter mainAdapter = new ViewPagerHoatDongAdapter(getFragmentManager());
        mainAdapter.addFragment(HoatDongComingFragment.getInstance(), getString(R.string.coming));
        mainAdapter.addFragment(HoatDongFinishedFragment.getInstance(), getString(R.string.finish));
        mViewpager.setAdapter(mainAdapter);
        mTabLayout.setupWithViewPager(mViewpager);
    }

}
