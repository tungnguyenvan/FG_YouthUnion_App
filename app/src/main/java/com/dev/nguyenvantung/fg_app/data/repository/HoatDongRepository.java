package com.dev.nguyenvantung.fg_app.data.repository;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.CheckInRequest;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDongRequest;
import com.dev.nguyenvantung.fg_app.data.source.HoatDongDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.checkin.CheckInResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongsResponse;

import io.reactivex.Single;

public class HoatDongRepository implements HoatDongDataSource.LocalDataSource, HoatDongDataSource.RemoteDataSource{
    private HoatDongDataSource.LocalDataSource mLocalDataSource;
    private HoatDongDataSource.RemoteDataSource mRemoteDataSource;

    public HoatDongRepository(HoatDongDataSource.LocalDataSource localDataSource, HoatDongDataSource.RemoteDataSource remoteDataSource) {
        this.mLocalDataSource = localDataSource;
        this.mRemoteDataSource =remoteDataSource;
    }

    @Override
    public Single<HoatDongsResponse> listHoatDongFinished(String token) {
        return mRemoteDataSource.listHoatDongFinished(token);
    }

    @Override
    public Single<HoatDongsResponse> listHoatDongComming(String token) {
        return mRemoteDataSource.listHoatDongComming(token);
    }

    @Override
    public Single<HoatDongsResponse> store(String token, HoatDongRequest hoatDongRequest) {
        return mRemoteDataSource.store(token, hoatDongRequest);
    }

    @Override
    public Single<HoatDongResponse> show(String toke, int id) {
        return mRemoteDataSource.show(toke, id);
    }

    @Override
    public Single<CheckInResponse> checkIn(String token, CheckInRequest checkInRequest) {
        return mRemoteDataSource.checkIn(token, checkInRequest);
    }
}
