package com.dev.nguyenvantung.fg_app.data.repository;

import com.dev.nguyenvantung.fg_app.data.source.HoatDongTypeDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.HoatDongType.HoatDongTypeReponse;

import io.reactivex.Single;

public class HoatDongTypeRepository implements HoatDongTypeDataSource.LocalDataSource, HoatDongTypeDataSource.RemoteDataSource {
    private HoatDongTypeDataSource.LocalDataSource mLocal;
    private HoatDongTypeDataSource.RemoteDataSource mRemote;

    public HoatDongTypeRepository(HoatDongTypeDataSource.LocalDataSource mLocal, HoatDongTypeDataSource.RemoteDataSource mRemote) {
        this.mLocal = mLocal;
        this.mRemote = mRemote;
    }

    @Override
    public Single<HoatDongTypeReponse> listHoatDongType(String token) {
        return mRemote.listHoatDongType(token);
    }
}
