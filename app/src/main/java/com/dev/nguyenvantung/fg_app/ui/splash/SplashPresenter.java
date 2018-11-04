package com.dev.nguyenvantung.fg_app.ui.splash;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;
import com.dev.nguyenvantung.fg_app.data.repository.AuthRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.login.LoginResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

public class SplashPresenter implements SplashContract.Presenter {
    private static final String TAG = SplashPresenter.class.getName();
    private SplashContract.View mView;
    private AuthRepository mAuthRepository;
    private SchedulerProvider mSchedulerProvider;

    public SplashPresenter(AuthRepository mAuthRepository, SchedulerProvider mSchedulerProvider){
        this.mAuthRepository = mAuthRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void setView(SplashContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void login(LoginRequesst mLoginRequesst) {
        mAuthRepository.login(mLoginRequesst)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(loginResponse -> handleSuccess(loginResponse),
                        error -> handleError(error));
    }

    private void handleSuccess(LoginResponse mLoginResponse){
        mView.loginSuccess(mLoginResponse);
    }

    private void handleError(Throwable e){
        Log.e(TAG, e.getMessage());
        mView.loginError();
    }
}
