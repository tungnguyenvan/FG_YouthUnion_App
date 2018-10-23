package com.dev.nguyenvantung.fg_app.data.source;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;

import io.reactivex.Single;

public interface HoatDongDataSource {
    interface LocalDataSource{

    }
    interface RemoteDataSource{
        Single<HoatDongResponse> listHoatDongFinished(String token);
        Single<HoatDongResponse> listHoatDongComming(String token);
    }
}
