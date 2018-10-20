package com.dev.nguyenvantung.fg_app.data.source.remote.api;

import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoandetail.LCDoanDetailResponse;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiLCDoanDetail {
    @GET("users")
    Single<LCDoanDetailResponse> listUser(@Header(AppConstants.AUTHORIZATION) String token);

    @GET("lcdoans/{id}")
    Single<LCDoanDetailResponse> LCDoan(@Header(AppConstants.AUTHORIZATION) String token, @Path(AppConstants.ID) int id);
}
