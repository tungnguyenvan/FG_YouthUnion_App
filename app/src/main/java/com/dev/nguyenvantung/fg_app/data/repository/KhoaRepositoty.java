package com.dev.nguyenvantung.fg_app.data.repository;

import com.dev.nguyenvantung.fg_app.data.source.KhoaDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.khoa.KhoaResponse;

import io.reactivex.Single;

public class KhoaRepositoty implements KhoaDataSource.LocalDataSource, KhoaDataSource.RemoteDataSource{

    private KhoaDataSource.LocalDataSource mLocalDataSource;
    private KhoaDataSource.RemoteDataSource mRemoteDataSource;

    public KhoaRepositoty(KhoaDataSource.LocalDataSource mLocalDataSource, KhoaDataSource.RemoteDataSource mRemoteDataSource) {
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    @Override
    public Single<KhoaResponse> listKhoa(String token) {
        return mRemoteDataSource.listKhoa(token);
    }

}
