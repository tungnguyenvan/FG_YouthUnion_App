package com.dev.nguyenvantung.fg_app.data.repository;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDongRequest;
import com.dev.nguyenvantung.fg_app.data.source.StoreHoatDongDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.storehoatdong.StoreHoatDongResponse;

import io.reactivex.Single;

public class StoreHoatDongRepository implements StoreHoatDongDataSource.LocalDataSource, StoreHoatDongDataSource.RemoteDataSource {
    private StoreHoatDongDataSource.LocalDataSource mLocal;
    private StoreHoatDongDataSource.RemoteDataSource mRemote;

    public StoreHoatDongRepository(StoreHoatDongDataSource.LocalDataSource mLocal, StoreHoatDongDataSource.RemoteDataSource mRemote) {
        this.mLocal = mLocal;
        this.mRemote = mRemote;
    }

    @Override
    public Single<StoreHoatDongResponse> store(String token, HoatDongRequest hoatDongRequest) {
        return mRemote.store(token, hoatDongRequest);
    }
}
