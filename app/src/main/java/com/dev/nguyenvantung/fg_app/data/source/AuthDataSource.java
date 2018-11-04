package com.dev.nguyenvantung.fg_app.data.source;

import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.login.LoginResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.logout.LogOutResponse;

import io.reactivex.Single;

public interface AuthDataSource {
    interface Remote{
        Single<LoginResponse> login(LoginRequesst body);
        Single<LogOutResponse> logOut(String token);
    }
}
