package com.dev.nguyenvantung.fg_app.data.source;

import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.login.LoginResponse;

import io.reactivex.Single;

public interface LoginDataSource {
    interface Remote{
        Single<LoginResponse> login(LoginRequesst body);
    }
}
