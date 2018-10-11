package com.dev.nguyenvantung.fg_app.data.source;

import com.dev.nguyenvantung.fg_app.data.repository.UserRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;

import io.reactivex.Single;

public interface UserDataSource {
    interface LocalDataSource{

    }
    interface RemoteDataSource{
        Single<UserResponse> listUser(String token);
    }
}
