package com.dev.nguyenvantung.fg_app.data.source.remote;

import android.content.Context;

import com.dev.nguyenvantung.fg_app.data.source.UserHoatDongDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiUserHoatDong;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UsersResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.service.AppServiceClient;

import io.reactivex.Single;

public class UserHoatDongRemoteDataSource implements UserHoatDongDataSource.RemoteDataSource {
    private static UserHoatDongRemoteDataSource instance;
    private ApiUserHoatDong mApiUserHoatDong;

    private UserHoatDongRemoteDataSource(ApiUserHoatDong mApiUserHoatDong){
        this.mApiUserHoatDong = mApiUserHoatDong;
    }

    public static synchronized UserHoatDongRemoteDataSource getInstance(Context context){
        if (instance == null){
            instance = new UserHoatDongRemoteDataSource(AppServiceClient.getUserHoatDongRemoteInstance(context));
        }

        return instance;
    }

    @Override
    public Single<UsersResponse> listNotJoin(String token, int id) {
        return mApiUserHoatDong.notJoin(token, id);
    }

    @Override
    public Single<UsersResponse> listJoined(String token, int id) {
        return mApiUserHoatDong.joined(token, id);
    }
}
