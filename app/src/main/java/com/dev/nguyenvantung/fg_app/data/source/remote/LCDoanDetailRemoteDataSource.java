package com.dev.nguyenvantung.fg_app.data.source.remote;

import android.content.Context;

import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;
import com.dev.nguyenvantung.fg_app.data.source.LCDoanDataSource;
import com.dev.nguyenvantung.fg_app.data.source.LCDoanDetailDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiLCDoanDetail;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoan.LCDoanResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoandetail.LCDoanDetailResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.service.AppServiceClient;

import io.reactivex.Single;

public class LCDoanDetailRemoteDataSource implements LCDoanDetailDataSource.RemoteDataSource{
    private static LCDoanDetailRemoteDataSource instance;
    private ApiLCDoanDetail mApiLCDoanDetail;

    public LCDoanDetailRemoteDataSource(ApiLCDoanDetail mApiLCDoanDetail) {
        this.mApiLCDoanDetail = mApiLCDoanDetail;
    }

    public static synchronized LCDoanDetailRemoteDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new LCDoanDetailRemoteDataSource(AppServiceClient.getLCDoanDetailRemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Single<LCDoanDetailResponse> listUser(String token) {
        return mApiLCDoanDetail.listUser(token);
    }

    @Override
    public Single<LCDoanDetailResponse> getLCDoan(String token, int id) {
        return mApiLCDoanDetail.LCDoan(token, id);
    }

}
