package com.dev.nguyenvantung.fg_app.ui.main;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.ui.main.fragment.coming.ComingFragment;
import com.dev.nguyenvantung.fg_app.ui.main.fragment.end.EndFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity{
    @BindView(R.id.main_viewpager)
    public ViewPager main_viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        initViewPager();
    }

    private void initViewPager() {
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.addFragment(ComingFragment.getInstance());
        mainAdapter.addFragment(EndFragment.getInstance());
        main_viewpager.setAdapter(mainAdapter);
    }


}
