package com.dev.nguyenvantung.fg_app.data.source;

import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UsersResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.userhoatdong.UserHoatDongResponse;

import io.reactivex.Single;

public interface UserHoatDongDataSource {
    interface LocalDataSource{

    }
    interface RemoteDataSource {
        Single<UsersResponse> listNotJoin(String token, int id);
        Single<UserHoatDongResponse> listJoined(String token, int id);
    }
}
