package com.dev.nguyenvantung.fg_app.data.source.remote;

import android.content.Context;
import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.source.KhoaDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiKhoa;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.khoa.KhoaResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.service.AppServiceClient;

import io.reactivex.Single;

public class KhoaRemoteDataSource implements KhoaDataSource.RemoteDataSource{
    private static KhoaRemoteDataSource instance;
    private ApiKhoa mApiKhoa;

    public KhoaRemoteDataSource(ApiKhoa mApiKhoa) {
        this.mApiKhoa = mApiKhoa;
    }

    public static synchronized KhoaRemoteDataSource getInstance(Context context) {
        if (instance == null) {
            instance = new KhoaRemoteDataSource(AppServiceClient.getKhoaRemoteInstance(context));
        }
        return instance;
    }


    @Override
    public Single<KhoaResponse> listKhoa(String token) {
        return mApiKhoa.listKhoa(token);
    }

}
