package com.dev.nguyenvantung.fg_app.data.source.remote.api;

import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiUser {

    @GET("users/")
    Single<UserResponse> listUser(@Header(AppConstants.AUTHORIZATION) String token);

    @GET("users/lcdoan/{id}")
    Single<UserResponse> listUserLCDoan(@Header(AppConstants.AUTHORIZATION) String token, @Path(AppConstants.ID) int id);
}
