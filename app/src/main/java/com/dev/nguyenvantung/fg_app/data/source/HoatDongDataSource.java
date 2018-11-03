package com.dev.nguyenvantung.fg_app.data.source;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.CheckInRequest;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDongRequest;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.checkin.CheckInResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongsResponse;

import io.reactivex.Single;

public interface HoatDongDataSource {
    interface LocalDataSource{

    }
    interface RemoteDataSource{
        Single<HoatDongsResponse> listHoatDongFinished(String token);
        Single<HoatDongsResponse> listHoatDongComming(String token);
        Single<HoatDongsResponse> store(String token, HoatDongRequest hoatDongRequest);
        Single<HoatDongResponse> show(String token, int id);
        Single<CheckInResponse> checkIn(String token, CheckInRequest checkInRequest);
    }
}
