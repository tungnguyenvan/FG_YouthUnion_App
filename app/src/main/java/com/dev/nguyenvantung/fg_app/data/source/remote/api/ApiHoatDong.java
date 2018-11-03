package com.dev.nguyenvantung.fg_app.data.source.remote.api;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.CheckInRequest;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDongRequest;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.checkin.CheckInResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongsResponse;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiHoatDong {
    @GET("finished/hoatdongs")
    Single<HoatDongsResponse> listHoatDongFinished(@Header(AppConstants.AUTHORIZATION) String token);

    @GET("comingup/hoatdongs")
    Single<HoatDongsResponse> listHoatDongComing(@Header(AppConstants.AUTHORIZATION) String token);

    @GET("hoatdongs/{id}")
    Single<HoatDongResponse> show(@Header(AppConstants.AUTHORIZATION) String token, @Path("id") int id);

    @POST("hoatdongs")
    Single<HoatDongsResponse> store(@Header(AppConstants.AUTHORIZATION) String token, @Body HoatDongRequest hoatDongRequest);

    @POST("userhoatdongs")
    Single<CheckInResponse> checkin(@Header(AppConstants.AUTHORIZATION) String token, @Body CheckInRequest checkInRequest);
}
