package com.dev.nguyenvantung.fg_app.data.source.remote;

import android.content.Context;

import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;
import com.dev.nguyenvantung.fg_app.data.source.LoginDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiLogin;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.login.LoginResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.service.AppServiceClient;

import io.reactivex.Single;

public class LoginRemoteDataSource implements LoginDataSource.Remote {
    private static LoginRemoteDataSource instance;
    private ApiLogin mApiLogin;

    public LoginRemoteDataSource(ApiLogin mApiLogin){
        this.mApiLogin = mApiLogin;
    }

    public static synchronized LoginRemoteDataSource getInstance(Context context){
        if (instance == null){
            instance = new LoginRemoteDataSource(AppServiceClient.getLoginRemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Single<LoginResponse> login(LoginRequesst body) {
        return mApiLogin.login(body);
    }
}
