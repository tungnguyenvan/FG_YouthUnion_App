package com.dev.nguyenvantung.fg_app.data.repository;

import com.dev.nguyenvantung.fg_app.data.source.LCDoanDetailDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoandetail.LCDoanDetailResponse;

import io.reactivex.Single;

public class LCDoanDetailRepository implements LCDoanDetailDataSource.LocalDataSource, LCDoanDetailDataSource.RemoteDataSource{

    private LCDoanDetailDataSource.LocalDataSource mLocalDataSource;
    private LCDoanDetailDataSource.RemoteDataSource mRemoteDataSource;

    public LCDoanDetailRepository(LCDoanDetailDataSource.LocalDataSource mLocalDataSource, LCDoanDetailDataSource.RemoteDataSource mRemoteDataSource) {
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    @Override
    public Single<LCDoanDetailResponse> listUser(String token) {
        return mRemoteDataSource.listUser(token);
    }
}
