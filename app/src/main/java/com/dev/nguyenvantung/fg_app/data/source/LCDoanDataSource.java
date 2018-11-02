package com.dev.nguyenvantung.fg_app.data.source;

import com.dev.nguyenvantung.fg_app.data.source.remote.response.khoa.KhoaResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoan.LCDoanResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoandetail.LCDoanDetailResponse;

import io.reactivex.Single;

public interface LCDoanDataSource {
    interface LocalDataSource{

    }
    interface RemoteDataSource{
        Single<LCDoanResponse> listLCDoan(String token);
        Single<LCDoanDetailResponse> LCDoan(String token, int id);
    }
}
