package com.dev.nguyenvantung.fg_app.data.source.remote;

import android.content.Context;

import com.dev.nguyenvantung.fg_app.data.source.HoatDongDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiHoatDong;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.service.AppServiceClient;

import io.reactivex.Single;

public class HoatDongRemoteDataSource implements HoatDongDataSource.RemoteDataSource{
    private static HoatDongRemoteDataSource instance;
    private ApiHoatDong mApiHoatDong;

    public HoatDongRemoteDataSource(ApiHoatDong mApiHoatDong){
        this.mApiHoatDong = mApiHoatDong;
    }

    public static synchronized HoatDongRemoteDataSource getInstance(Context context){
        if (instance == null){
            instance = new HoatDongRemoteDataSource(AppServiceClient.getHoatDongRemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Single<HoatDongResponse> listHoatDongFinished(String token) {
        return mApiHoatDong.listHoatDongFinished(token);
    }

    @Override
    public Single<HoatDongResponse> listHoatDongComming(String token) {
        return mApiHoatDong.listHoatDongComing(token);
    }
}
