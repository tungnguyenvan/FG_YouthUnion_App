package com.dev.nguyenvantung.fg_app.data.repository;

import android.util.Log;
import com.dev.nguyenvantung.fg_app.data.source.UserDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UsersResponse;

import io.reactivex.Single;

public class UserRepository implements UserDataSource.LocalDataSource, UserDataSource.RemoteDataSource {
    private UserDataSource.LocalDataSource mLocalDataSource;
    private UserDataSource.RemoteDataSource mRemoteDataSource;

    public UserRepository(UserDataSource.LocalDataSource mLocalDataSource, UserDataSource.RemoteDataSource mRemoteDataSource){
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }


    @Override
    public Single<UsersResponse> listUser(String token) {
        return mRemoteDataSource.listUser(token);
    }

    @Override
    public Single<UsersResponse> listUserLCDoan(String token, int id) {
        return mRemoteDataSource.listUserLCDoan(token, id);
    }

    @Override
    public Single<UserResponse> userDetail(String token, int id) {
        return mRemoteDataSource.userDetail(token, id);
    }
}
