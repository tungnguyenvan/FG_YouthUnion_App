package com.dev.nguyenvantung.fg_app.ui.storehoatdong;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.repository.StoreHoatDongRepository;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

public class StoreHoatDongPresenter implements StoreHoatDongConstact.Presenter {
    private static final String TAG = StoreHoatDongPresenter.class.getName();
    private StoreHoatDongConstact.Presenter mPresenter;
    private StoreHoatDongRepository mRepository;
    private SchedulerProvider mSchedulerProvider;

    public StoreHoatDongPresenter(StoreHoatDongRepository mRepository, SchedulerProvider mSchedulerProvider){
        this.mRepository = mRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void storeHoatDong(HoatDong token) {

    }

    @Override
    public void setView(StoreHoatDongConstact.View mView) {

    }
}
