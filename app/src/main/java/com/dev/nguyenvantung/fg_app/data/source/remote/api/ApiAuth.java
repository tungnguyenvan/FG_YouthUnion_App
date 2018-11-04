package com.dev.nguyenvantung.fg_app.data.source.remote.api;

import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.login.LoginResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.logout.LogOutResponse;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiAuth {

    @POST("login")
    Single<LoginResponse> login(@Body LoginRequesst body);

    @GET("logout")
    Single<LogOutResponse> logOut(@Header(AppConstants.AUTHORIZATION) String token);
}
