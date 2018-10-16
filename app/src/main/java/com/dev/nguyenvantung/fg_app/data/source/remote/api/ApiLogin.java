package com.dev.nguyenvantung.fg_app.data.source.remote.api;

import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.login.LoginResponse;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiLogin {

    @POST("login")
    Single<LoginResponse> login(@Body LoginRequesst body);
}
