package com.dev.nguyenvantung.fg_app.data.source.remote.api;

import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiUser {

    @GET("users/")
    Single<UserResponse> listUser(@Header("Authorization") String token);
}
