package com.dev.nguyenvantung.fg_app.ui.userjoined;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.UserHoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.userhoatdong.UserHoatDongResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

public class UserJoinedPresenter implements UserJoinedContract.Presenter {
    private static final String TAG = UserJoinedPresenter.class.getName();
    private UserHoatDongRepository mUserHoatDongRepository;
    private SchedulerProvider mSchedulerProvider;
    private UserJoinedContract.View mView;

    public UserJoinedPresenter(UserHoatDongRepository mUserHoatDongRepository, SchedulerProvider mSchedulerProvider){
        this.mUserHoatDongRepository = mUserHoatDongRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void setView(UserJoinedContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void listUserJoined(String token, int id) {
        mView.showProgress();
        mUserHoatDongRepository.listJoined(token, id)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(userHoatDongResponse -> handleSuccess(userHoatDongResponse),
                        error -> handleFail(error));
    }

    public void handleSuccess(UserHoatDongResponse mUsersResponse){
        mView.dimissProgress();
        Log.d(TAG, mUsersResponse.getData().size() + "-" + mUsersResponse.getData().get(1).getUser().getUsername());
        mView.setListUser(mUsersResponse.getData());
    }

    public void handleFail(Throwable error){
        mView.dimissProgress();
        Log.e(TAG, error.getLocalizedMessage());
    }
}
