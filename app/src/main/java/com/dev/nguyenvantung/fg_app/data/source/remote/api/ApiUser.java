package com.dev.nguyenvantung.fg_app.data.source.remote.api;

import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UsersResponse;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiUser {

    @GET("users/")
    Single<UsersResponse> listUser(@Header(AppConstants.AUTHORIZATION) String token);

    @GET("users/lcdoan/{id}")
    Single<UsersResponse> listUserLCDoan(@Header(AppConstants.AUTHORIZATION) String token, @Path(AppConstants.ID) int id);

    @GET("users/detail/{id}")
    Single<UserResponse> userDetail(@Header(AppConstants.AUTHORIZATION) String token, @Path(AppConstants.ID) int id);
}
