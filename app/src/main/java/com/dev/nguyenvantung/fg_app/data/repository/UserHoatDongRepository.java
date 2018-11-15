package com.dev.nguyenvantung.fg_app.data.repository;

import com.dev.nguyenvantung.fg_app.data.source.UserHoatDongDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UsersResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.userhoatdong.UserHoatDongResponse;

import io.reactivex.Single;

public class UserHoatDongRepository implements UserHoatDongDataSource.LocalDataSource, UserHoatDongDataSource.RemoteDataSource {
    private UserHoatDongDataSource.LocalDataSource mLocal;
    private UserHoatDongDataSource.RemoteDataSource mRemote;

    public UserHoatDongRepository(UserHoatDongDataSource.LocalDataSource mLocal, UserHoatDongDataSource.RemoteDataSource mRemote) {
        this.mLocal = mLocal;
        this.mRemote = mRemote;
    }

    @Override
    public Single<UsersResponse> listNotJoin(String token, int id) {
        return mRemote.listNotJoin(token, id);
    }

    @Override
    public Single<UserHoatDongResponse> listJoined(String token, int id){
        return mRemote.listJoined(token, id);
    }
}
