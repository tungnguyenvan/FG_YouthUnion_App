package com.dev.nguyenvantung.fg_app.data.source.remote.api;

import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiHoatDong {
    @GET("finished/hoatdongs")
    Single<HoatDongResponse> listHoatDongFinished(@Header("Authorization") String token);
    @GET("comingup/hoatdongs")
    Single<HoatDongResponse> listHoatDongComing(@Header("Authorization") String token);
}
