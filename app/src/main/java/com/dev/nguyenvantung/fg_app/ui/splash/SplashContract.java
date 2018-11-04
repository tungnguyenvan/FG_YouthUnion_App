package com.dev.nguyenvantung.fg_app.ui.splash;

import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.login.LoginResponse;

public interface SplashContract {
    interface View{
        void loginSuccess(LoginResponse mLoginResponse);
        void loginError();
    }
    interface Presenter{
        void setView(View mView);
        void login(LoginRequesst mLoginRequesst);
    }
}
