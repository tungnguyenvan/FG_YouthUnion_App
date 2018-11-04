package com.dev.nguyenvantung.fg_app.ui.login;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;
import com.dev.nguyenvantung.fg_app.data.repository.AuthRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.login.LoginResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

public class LoginPresenter implements LoginConstact.Presenter{
    private static final String TAG = "LoginPresenter";
    private LoginConstact.View mView;
    private AuthRepository mRepository;
    private SchedulerProvider mSchedulerProvider;

    public LoginPresenter (AuthRepository mRepository, SchedulerProvider mSchedulerProvider){
        this.mRepository = mRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void login(LoginRequesst body) {
        mView.showProgressbarDialog();
        mRepository.login(body)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(loginResponse -> handleSuccess(loginResponse),
                        e -> handleError(e));
    }

    @Override
    public void setView(LoginConstact.View mView) {
        this.mView = mView;
    }

    private void handleSuccess(LoginResponse mLoginResponse){
        mView.dimissProgressbarDialog();
        mView.loginSuccess(mLoginResponse);
    }

    private void handleError(Throwable e){
        Log.e(TAG, e.toString());
        mView.dimissProgressbarDialog();
        mView.loginFails();
    }
}
