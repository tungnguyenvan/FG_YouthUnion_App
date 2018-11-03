package com.dev.nguyenvantung.fg_app.ui.storehoatdong;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDongRequest;
import com.dev.nguyenvantung.fg_app.data.repository.HoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.repository.HoatDongTypeRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.HoatDongType.HoatDongTypeReponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongsResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

public class StoreHoatDongPresenter implements StoreHoatDongConstact.Presenter {
    private static final String TAG = StoreHoatDongPresenter.class.getName();
    private HoatDongRepository mRepository;
    private HoatDongTypeRepository mHoatDongTypeRepository;
    private SchedulerProvider mSchedulerProvider;
    private StoreHoatDongConstact.View mView;

    public StoreHoatDongPresenter(HoatDongTypeRepository mHoatDongTypeRepository, HoatDongRepository mRepository, SchedulerProvider mSchedulerProvider){
        this.mHoatDongTypeRepository = mHoatDongTypeRepository;


        this.mRepository = mRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void listHoatDongType(String token) {
        mView.showProgressbar();
        mHoatDongTypeRepository.listHoatDongType(token)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(hoatDongTypeReponse -> handleSuccess(hoatDongTypeReponse),
                        error -> handleError(error));
    }

    @Override
    public void storeHoatDong(String token, HoatDongRequest hoatDongRequest) {
        mView.showProgressbar();
        mRepository.store(token, hoatDongRequest)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(hoatDongResponse -> handleSuccess(hoatDongResponse),
                        e -> handleError(e));
    }

    @Override
    public void setView(StoreHoatDongConstact.View mView) {
        this.mView = mView;
    }

    private void handleSuccess(HoatDongTypeReponse hoatDongTypeReponse){
        mView.setListHoatDongType(hoatDongTypeReponse.getData());
        mView.dimissProgressbar();
    }

    private void handleSuccess(HoatDongsResponse hoatDongsResponse){
        mView.createHoatDongSuccess(hoatDongsResponse);
        mView.dimissProgressbar();
    }

    private void handleError(Throwable e){
        Log.e(TAG, "Error: " + e.toString());
        mView.dimissProgressbar();
    }
}
