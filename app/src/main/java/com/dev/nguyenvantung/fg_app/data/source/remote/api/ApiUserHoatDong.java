package com.dev.nguyenvantung.fg_app.data.source.remote.api;

import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UsersResponse;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiUserHoatDong {

    @GET("userhoatdongs/hoatdongs/{id}")
    Single<UsersResponse> notJoin(@Header(AppConstants.AUTHORIZATION) String token, @Path(AppConstants.ID) int id);
}
