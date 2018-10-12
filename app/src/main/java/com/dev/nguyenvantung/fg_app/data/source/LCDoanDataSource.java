package com.dev.nguyenvantung.fg_app.data.source;

import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoan.LCDoanResponse;

import io.reactivex.Single;

public interface LCDoanDataSource {
    interface LocalDataSource{

    }
    interface RemoteDataSource{
        Single<LCDoanResponse> listLCDoan(String token);
    }
}
