package com.dev.nguyenvantung.fg_app.data.repository;

import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;
import com.dev.nguyenvantung.fg_app.data.source.LoginDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.LoginRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.login.LoginResponse;

import io.reactivex.Single;

public class LoginRepository implements LoginDataSource.Remote {
    private LoginDataSource.Remote mRemoteDataSource;

    public LoginRepository(LoginRemoteDataSource mRemoteDataSource){
        this.mRemoteDataSource = mRemoteDataSource;
    }

    @Override
    public Single<LoginResponse> login(LoginRequesst body) {
        return mRemoteDataSource.login(body);
    }
}
