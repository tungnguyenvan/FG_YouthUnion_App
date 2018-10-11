package com.dev.nguyenvantung.fg_app.data.repository;

import com.dev.nguyenvantung.fg_app.data.source.UserDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;

import io.reactivex.Single;

public class UserRepository implements UserDataSource.LocalDataSource, UserDataSource.RemoteDataSource {
    private UserDataSource.LocalDataSource mLocalDataSource;
    private UserDataSource.RemoteDataSource mRemoteDataSource;

    public UserRepository(UserDataSource.LocalDataSource mLocalDataSource, UserDataSource.RemoteDataSource mRemoteDataSource){
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }


    @Override
    public Single<UserResponse> listUser(String token) {
        return mRemoteDataSource.listUser(token);
    }
}
