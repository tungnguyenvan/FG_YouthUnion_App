package com.dev.nguyenvantung.fg_app.ui.main;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.repository.HoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.source.local.HoatDongLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.HoatDongRemoteDataSource;
import com.dev.nguyenvantung.fg_app.ui.main.fragment.adapter.MainAdapter;
import com.dev.nguyenvantung.fg_app.ui.main.fragment.coming.HoatDongComingFragment;
import com.dev.nguyenvantung.fg_app.ui.main.fragment.end.HoatDongEndFragment;
import com.dev.nguyenvantung.fg_app.ui.main.fragment.end.HoatDongEndedConstract;
import com.dev.nguyenvantung.fg_app.ui.main.fragment.end.HoatDongEndedPresenter;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HoatDongEndedConstract.View{
    @BindView(R.id.main_viewpager)
    public ViewPager main_viewpager;

    private HoatDongEndedConstract.Presenter mPresenter;
    private com.dev.nguyenvantung.fg_app.ui.main.fragment.coming.HoatDongComingConstract.View mViewComing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViewPager();
        mViewComing = HoatDongComingFragment.getMview();

        AppPref.getInstance(this).putApiToken(AppConstants.TOKEN);
        HoatDongRepository hoatDongRepository = new HoatDongRepository(HoatDongLocalDataSource.getInstance(),
                HoatDongRemoteDataSource.getInstance(this));
        mPresenter = new HoatDongEndedPresenter(hoatDongRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);
        mPresenter.listHoatDong(AppConstants.BEARER + AppPref.getInstance(this).getApiToken());
    }

    private void initViewPager() {
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mainAdapter.addFragment(HoatDongEndFragment.getInstance());
        mainAdapter.addFragment(HoatDongComingFragment.getInstance());
        main_viewpager.setAdapter(mainAdapter);
    }

    @Override
    public void setListHoatDong(List<HoatDong> listHoatDong) {
        mViewComing.setData(listHoatDong);
    }
}
