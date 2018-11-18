package com.dev.nguyenvantung.fg_app.ui.login;

import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.login.LoginResponse;

public interface LoginConstact {
    interface View{
        void showProgressbarDialog();
        void dimissProgressbarDialog();
        void loginSuccess(LoginResponse mLoginResponse);
        void loginFails();
    }
    interface Presenter{
        void login(LoginRequesst body);
        void setView(View mView);
    }
}
