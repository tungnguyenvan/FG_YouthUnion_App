package com.dev.nguyenvantung.fg_app.data.repository;

import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;
import com.dev.nguyenvantung.fg_app.data.source.AuthDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.AuthRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.login.LoginResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.logout.LogOutResponse;

import io.reactivex.Single;

public class AuthRepository implements AuthDataSource.Remote {
    private AuthDataSource.Remote mRemoteDataSource;

    public AuthRepository(AuthRemoteDataSource mRemoteDataSource){
        this.mRemoteDataSource = mRemoteDataSource;
    }

    @Override
    public Single<LoginResponse> login(LoginRequesst body) {
        return mRemoteDataSource.login(body);
    }

    @Override
    public Single<LogOutResponse> logOut(String token) {
        return mRemoteDataSource.logOut(token);
    }
}
