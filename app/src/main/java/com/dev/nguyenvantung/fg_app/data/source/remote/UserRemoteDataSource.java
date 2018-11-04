package com.dev.nguyenvantung.fg_app.data.source.remote;

import android.content.Context;

import com.dev.nguyenvantung.fg_app.data.source.UserDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiUser;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UsersResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.service.AppServiceClient;

import io.reactivex.Single;

public class UserRemoteDataSource implements UserDataSource.RemoteDataSource {
    private static UserRemoteDataSource instance;
    private ApiUser mApiUser;

    public UserRemoteDataSource(ApiUser mApiUser){
        this.mApiUser = mApiUser;
    }

    public static synchronized UserRemoteDataSource getInstance(Context context){
        if (instance == null){
            instance = new UserRemoteDataSource(AppServiceClient.getUserRemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Single<UsersResponse> listUser(String token) {
        return mApiUser.listUser(token);
    }

    @Override
    public Single<UsersResponse> listUserLCDoan(String token, int id) {
        return mApiUser.listUserLCDoan(token, id);
    }

    @Override
    public Single<UserResponse> userDetail(String token, int id) {
        return mApiUser.userDetail(token, id);
    }
}
