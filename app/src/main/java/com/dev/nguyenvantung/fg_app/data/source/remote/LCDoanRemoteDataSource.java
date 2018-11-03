package com.dev.nguyenvantung.fg_app.data.source.remote;

import android.content.Context;

import com.dev.nguyenvantung.fg_app.data.source.LCDoanDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiKhoa;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiLCDoan;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.khoa.KhoaResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoan.LCDoanResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoandetail.LCDoanDetailResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.service.AppServiceClient;

import io.reactivex.Single;

public class LCDoanRemoteDataSource implements LCDoanDataSource.RemoteDataSource {
    private static LCDoanRemoteDataSource instance;
    private ApiLCDoan mApiLCDoan;

    public LCDoanRemoteDataSource(ApiLCDoan mApiLCDoan) {
        this.mApiLCDoan = mApiLCDoan;
    }

    public static synchronized LCDoanRemoteDataSource getInstance(Context context){
        if (instance == null){
            instance = new LCDoanRemoteDataSource(AppServiceClient.getLCDoanRemoteInstance(context));
        }
        return instance;
    }

    @Override
    public Single<LCDoanResponse> listLCDoan(String token) {
        return mApiLCDoan.listLCDoan(token);
    }

    @Override
    public Single<LCDoanDetailResponse> LCDoan(String token, int id) {
        return mApiLCDoan.LCDoan(token, id);
    }


}
