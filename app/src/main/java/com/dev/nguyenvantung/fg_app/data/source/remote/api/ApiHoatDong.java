package com.dev.nguyenvantung.fg_app.data.source.remote.api;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDongRequest;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.storehoatdong.StoreHoatDongResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiHoatDong {
    @GET("finished/hoatdongs")
    Single<HoatDongResponse> listHoatDongFinished(@Header("Authorization") String token);
    @GET("comingup/hoatdongs")
    Single<HoatDongResponse> listHoatDongComing(@Header("Authorization") String token);
    @GET("hoatdongs/")
    Single<HoatDongResponse> listHoatDong(@Header("Authorization") String token);
    @POST("hoatdongs/")
    Single<HoatDongResponse> store(@Header(AppConstants.AUTHORIZATION) String token, @Body HoatDongRequest hoatDongRequest);
}
