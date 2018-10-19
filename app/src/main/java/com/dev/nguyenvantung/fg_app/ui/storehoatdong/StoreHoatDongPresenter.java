package com.dev.nguyenvantung.fg_app.ui.storehoatdong;

import com.dev.nguyenvantung.fg_app.data.repository.HoatDongTypeRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdongtype.HoatDongTypeResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

public class StoreHoatDongPresenter implements StoreHoatDongConstact.Presenter {
    private static final String TAG = "StoreHoatDongPresenter";
    private StoreHoatDongConstact.View mView;
    private HoatDongTypeRepository mHoatDongTypeRepository;
    private SchedulerProvider mSchedulerProvider;

    public StoreHoatDongPresenter(HoatDongTypeRepository mHoatDongTypeRepository, SchedulerProvider mSchedulerProvider) {
        this.mHoatDongTypeRepository = mHoatDongTypeRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void listHoatDongType(String token) {
        mView.showProgressbar();
        mHoatDongTypeRepository.listHoatDongType(token)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(hoatDongTypeResponse -> handleHoatDongTypeSuccess(hoatDongTypeResponse),
                        error -> handleHoatDongTypeError(error));
    }

    private void handleHoatDongTypeSuccess(HoatDongTypeResponse hoatDongTypeResponse){
        mView.dimissProgressbar();
    }

    private void handleHoatDongTypeError(Throwable error){
        mView.dimissProgressbar();
    }
}
