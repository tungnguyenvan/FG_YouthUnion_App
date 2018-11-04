package com.dev.nguyenvantung.fg_app.ui.splash;

import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;

public interface SplashContract {
    interface View{
        void loginSuccess();
        void loginError();
    }
    interface Presenter{
        void setView(View mView);
        void login(LoginRequesst mLoginRequesst);
    }
}
