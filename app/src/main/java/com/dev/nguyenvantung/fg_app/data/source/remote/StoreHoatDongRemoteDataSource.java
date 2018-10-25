package com.dev.nguyenvantung.fg_app.data.source.remote;

import android.content.Context;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDongRequest;
import com.dev.nguyenvantung.fg_app.data.source.StoreHoatDongDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiHoatDong;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.storehoatdong.StoreHoatDongResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.service.AppServiceClient;

import io.reactivex.Single;

public class StoreHoatDongRemoteDataSource implements StoreHoatDongDataSource.RemoteDataSource {
    private static StoreHoatDongRemoteDataSource instance;
    private ApiHoatDong mApiHoatDong;

    public StoreHoatDongRemoteDataSource(ApiHoatDong mApiHoatDong){
        this.mApiHoatDong = mApiHoatDong;
    }

    public static synchronized StoreHoatDongRemoteDataSource getInstance(Context context){
        if (instance == null){
            instance = new StoreHoatDongRemoteDataSource(AppServiceClient.getHoatDongRemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Single<StoreHoatDongResponse> store(String token, HoatDongRequest hoatDongRequest) {
        return mApiHoatDong.store(token, hoatDongRequest);
    }
}
