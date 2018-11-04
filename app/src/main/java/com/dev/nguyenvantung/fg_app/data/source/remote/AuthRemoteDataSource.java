package com.dev.nguyenvantung.fg_app.data.source.remote;

import android.content.Context;

import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;
import com.dev.nguyenvantung.fg_app.data.source.AuthDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiAuth;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.login.LoginResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.logout.LogOutResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.service.AppServiceClient;

import io.reactivex.Single;

public class AuthRemoteDataSource implements AuthDataSource.Remote {
    private static AuthRemoteDataSource instance;
    private ApiAuth mApiAuth;

    public AuthRemoteDataSource(ApiAuth mApiAuth){
        this.mApiAuth = mApiAuth;
    }

    public static synchronized AuthRemoteDataSource getInstance(Context context){
        if (instance == null){
            instance = new AuthRemoteDataSource(AppServiceClient.getLoginRemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Single<LoginResponse> login(LoginRequesst body) {
        return mApiAuth.login(body);
    }

    @Override
    public Single<LogOutResponse> logOut(String token) {
        return mApiAuth.logOut(token);
    }
}
