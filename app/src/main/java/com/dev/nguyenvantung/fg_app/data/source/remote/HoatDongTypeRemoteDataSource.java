package com.dev.nguyenvantung.fg_app.data.source.remote;

import android.content.Context;

import com.dev.nguyenvantung.fg_app.data.source.HoatDongTypeDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiHoatDongType;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.HoatDongType.HoatDongTypeReponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.service.AppServiceClient;

import io.reactivex.Single;

public class HoatDongTypeRemoteDataSource implements HoatDongTypeDataSource.RemoteDataSource {
    private static HoatDongTypeRemoteDataSource instance;
    private ApiHoatDongType mApiHoatDongType;

    public HoatDongTypeRemoteDataSource(ApiHoatDongType mApiHoatDongType){
        this.mApiHoatDongType = mApiHoatDongType;
    }

    public static synchronized HoatDongTypeRemoteDataSource getTnstance(Context context){
        if (instance == null){
            instance = new HoatDongTypeRemoteDataSource(AppServiceClient.getHoatDongTypeRemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Single<HoatDongTypeReponse> listHoatDongType(String token) {
        return mApiHoatDongType.listHoatDongType(token);
    }
}
