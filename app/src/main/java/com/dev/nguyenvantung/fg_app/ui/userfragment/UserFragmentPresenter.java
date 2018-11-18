package com.dev.nguyenvantung.fg_app.ui.userfragment;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.UserHoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.repository.UserRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserDetailResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UsersResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.userhoatdong.UserHoatDongResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

public class UserFragmentPresenter implements UserFragmentContract.Presenter {
    private static final String TAG = UserFragmentPresenter.class.getName();
    private UserFragmentContract.View mView;
    private UserRepository mUserRepository;
    private UserHoatDongRepository mUserHoatDongRepository;
    private SchedulerProvider mSchedulerProvider;

    public UserFragmentPresenter(UserRepository mUserRepository, UserHoatDongRepository mUserHoatDongRepository
            , SchedulerProvider mSchedulerProvider) {
        this.mUserRepository = mUserRepository;
        this.mUserHoatDongRepository = mUserHoatDongRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void setView(UserFragmentContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getUser(String token, int id) {
        mView.showProgress();
        mUserRepository.show(token, id)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(userResponse -> handleSuccess(userResponse),
                        error -> handleError(error));
    }

    @Override
    public void getHoatDongJoined(String token, int id) {
        mView.showProgress();
        mUserHoatDongRepository.userJoined(token, id)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(userHoatDongResponse -> handleSuccess(userHoatDongResponse),
                        error -> handleError(error));
    }

    private void handleSuccess(UserResponse mUserResponse){
        mView.setDataUser(mUserResponse.getData());
        mView.dimissProgress();
    }

    private void handleSuccess(UserHoatDongResponse mUserHoatDongResponse){
        mView.setDataHoatDong(mUserHoatDongResponse.getData());
        mView.dimissProgress();
    }

    private void handleError(Throwable error){
        mView.dimissProgress();
        Log.e(TAG, error.getLocalizedMessage());
    }
}
