package com.dev.nguyenvantung.fg_app.ui.main;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.AuthRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.logout.LogOutResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

public class MainPresenter implements MainContract.Presenter {
    private static final String TAG = MainPresenter.class.getName();
    private MainContract.View mView;
    private AuthRepository mAuthRepository;
    private SchedulerProvider mSchedulerProvider;

    public MainPresenter(AuthRepository mAuthRepository, SchedulerProvider mSchedulerProvider) {
        this.mAuthRepository = mAuthRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void setView(MainContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void logOut(String token) {
        mView.showProgress();
        mAuthRepository.logOut(token)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(logOutResponse -> handleSuccess(logOutResponse),
                        e -> handleError(e));

    }

    private void handleSuccess(LogOutResponse mLogOutResponse){
        mView.dimissProgress();
        mView.logOutSuccess();
    }

    private void handleError(Throwable e){
        Log.e(TAG, e.getMessage());
        mView.dimissProgress();
        mView.logOutFail();
    }
}
