package com.dev.nguyenvantung.fg_app.data.source;

import com.dev.nguyenvantung.fg_app.data.source.remote.response.HoatDongType.HoatDongTypeReponse;

import io.reactivex.Single;

public interface HoatDongTypeDataSource {
    interface LocalDataSource{

    }
    interface RemoteDataSource{
        Single<HoatDongTypeReponse> listHoatDongType(String token);
    }
}
