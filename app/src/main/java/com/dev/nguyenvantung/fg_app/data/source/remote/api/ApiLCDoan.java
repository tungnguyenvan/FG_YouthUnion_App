package com.dev.nguyenvantung.fg_app.data.source.remote.api;

import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoan.LCDoanResponse;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiLCDoan {
    @GET("lcdoans")
    Single<LCDoanResponse> listLCDoan(@Header(AppConstants.AUTHORIZATION) String token);
}
