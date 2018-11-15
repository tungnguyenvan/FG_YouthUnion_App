package com.dev.nguyenvantung.fg_app.ui.hoatdongdetail;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.CheckInRequest;
import com.dev.nguyenvantung.fg_app.data.repository.HoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.repository.UserHoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.repository.UserRepository;
import com.dev.nguyenvantung.fg_app.data.source.UserHoatDongDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.checkin.CheckInResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UsersResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class HoatDongDetailPresenter implements HoatDongDetailContract.Presenter {
    private static final String TAG = HoatDongDetailPresenter.class.getName();
    private HoatDongDetailContract.View mView;
    private HoatDongRepository mHoatDongRepository;
    private UserHoatDongRepository mUserHoatDongRepository;
    private SchedulerProvider mProvider;

    public HoatDongDetailPresenter(HoatDongRepository mHoatDongRepository, UserHoatDongRepository
            mUserHoatDongRepository, SchedulerProvider mProvider) {
        this.mHoatDongRepository = mHoatDongRepository;
        this.mUserHoatDongRepository = mUserHoatDongRepository;
        this.mProvider = mProvider;
    }

    @Override
    public void setView(HoatDongDetailContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getHoatDong(String token, int id) {
        mView.showProgressbar();
        mHoatDongRepository.show(token, id)
                .subscribeOn(mProvider.io())
                .observeOn(mProvider.ui())
                .subscribe(hoatDongResponse -> handleSuccess(hoatDongResponse),
                        error -> handleError(error));
    }

    @Override
    public void listUserNotJoin(String token, int id) {
        mView.showProgressbar();
        mUserHoatDongRepository.listNotJoin(token, id)
                .subscribeOn(mProvider.io())
                .observeOn(mProvider.ui())
                .subscribe(usersResponse -> handleSuccess(usersResponse),
                        error -> handleError(error));
    }

    private void handleSuccess(UsersResponse usersResponse) {
        mView.dimissProgresbar();
        mView.setListUser(usersResponse.getData());
    }

    @Override
    public void checkIn(String token, CheckInRequest checkInRequest, int possion) {
        mView.showProgressbar();
        mUserHoatDongRepository.checkIn(token, checkInRequest)
                .subscribeOn(mProvider.io())
                .observeOn(mProvider.ui())
                .subscribe(checkInResponse -> handleSuccess(checkInResponse, possion),
                        error -> handleError(error));
    }

    private void handleSuccess(HoatDongResponse hoatDongResponse){
        mView.setHoatDong(hoatDongResponse.getData());
    }

    private void handleSuccess(CheckInResponse checkInResponse, int possion){
        mView.dimissProgresbar();
        mView.checkInSuccess(checkInResponse, possion);
    }

    private void handleError(Throwable e){
        Log.e(TAG, e.getLocalizedMessage());
        mView.dimissProgresbar();
    }
}
