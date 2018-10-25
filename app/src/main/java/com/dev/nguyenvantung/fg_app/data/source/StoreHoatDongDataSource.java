package com.dev.nguyenvantung.fg_app.data.source;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDongRequest;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.storehoatdong.StoreHoatDongResponse;

import io.reactivex.Single;

public interface StoreHoatDongDataSource {
    interface LocalDataSource{

    }
    interface RemoteDataSource{
        Single<StoreHoatDongResponse> store(String token, HoatDongRequest hoatDongRequest);
    }
}
