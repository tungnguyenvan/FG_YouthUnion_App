package com.dev.nguyenvantung.fg_app.data.source;

import com.dev.nguyenvantung.fg_app.data.model.userdetail.Users;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UsersResponse;

import io.reactivex.Single;

public interface UserDataSource {
    interface LocalDataSource{

    }
    interface RemoteDataSource{
        Single<UsersResponse> listUser(String token);
        Single<UsersResponse> listUserLCDoan(String token, int id);
        Single<UserResponse> userDetail(String token, int id);
    }
}
