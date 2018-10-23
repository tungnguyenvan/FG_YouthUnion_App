package com.dev.nguyenvantung.fg_app.data.repository;

import com.dev.nguyenvantung.fg_app.data.source.LCDoanDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.khoa.KhoaResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoan.LCDoanResponse;

import io.reactivex.Single;

public class LCDoanRepository implements LCDoanDataSource.LocalDataSource, LCDoanDataSource.RemoteDataSource {
    private LCDoanDataSource.LocalDataSource mLocalDataSource;
    private LCDoanDataSource.RemoteDataSource mRemoteDataSource;

    public LCDoanRepository(LCDoanDataSource.LocalDataSource mLocalDataSource, LCDoanDataSource.RemoteDataSource mRemoteDataSource){
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    @Override
    public Single<LCDoanResponse> listLCDoan(String token) {
        return mRemoteDataSource.listLCDoan(token);
    }

    @Override
    public Single<LCDoanResponse> listLCDById(String token, int id) {
        return mRemoteDataSource.listLCDById(token, id);
    }


}
