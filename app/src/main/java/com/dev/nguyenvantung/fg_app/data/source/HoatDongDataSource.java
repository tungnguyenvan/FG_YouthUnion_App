package com.dev.nguyenvantung.fg_app.data.source;

import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;

import io.reactivex.Single;

public interface HoatDongDataSource {
    interface LocalDataSource{

    }
    interface RemoteDataSource{
        Single<HoatDongResponse> listHoatDong(String token);
    }
}
