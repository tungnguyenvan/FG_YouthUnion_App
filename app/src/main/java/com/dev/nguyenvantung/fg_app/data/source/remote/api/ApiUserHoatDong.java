package com.dev.nguyenvantung.fg_app.data.source.remote.api;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.CheckInRequest;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.checkin.CheckInResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UsersResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.userhoatdong.UserHoatDongResponse;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiUserHoatDong {

    @GET("userhoatdongs/hoatdongs/notjoin/{id}")
    Single<UsersResponse> notJoin(@Header(AppConstants.AUTHORIZATION) String token, @Path(AppConstants.ID) int id);

    @GET("userhoatdongs/hoatdongs/{id}")
    Single<UserHoatDongResponse> joined(@Header(AppConstants.AUTHORIZATION) String token, @Path(AppConstants.ID) int id);

    @POST("userhoatdongs")
    Single<CheckInResponse> checkin(@Header(AppConstants.AUTHORIZATION) String token, @Body CheckInRequest checkInRequest);
}
