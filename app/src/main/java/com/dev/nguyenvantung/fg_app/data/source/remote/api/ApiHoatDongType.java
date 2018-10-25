package com.dev.nguyenvantung.fg_app.data.source.remote.api;

import com.dev.nguyenvantung.fg_app.data.source.remote.response.HoatDongType.HoatDongTypeReponse;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiHoatDongType {

    @GET("hoatdongtypes/")
    Single<HoatDongTypeReponse> listHoatDongType(@Header(AppConstants.AUTHORIZATION) String token);
}
