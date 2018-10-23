package com.dev.nguyenvantung.fg_app.data.source.remote.api;

import com.dev.nguyenvantung.fg_app.data.source.remote.response.khoa.KhoaResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoan.LCDoanResponse;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ApiLCDoan {
    @GET("lcdoans")
    Single<LCDoanResponse> listLCDoan(@Header(AppConstants.AUTHORIZATION) String token);

    @GET("lcdoans/{id}")
    Single<LCDoanResponse> listLCDById(@Header(AppConstants.AUTHORIZATION) String token, @Path(AppConstants.ID) int id);
}
