package com.dev.nguyenvantung.fg_app.data.source;


import com.dev.nguyenvantung.fg_app.data.source.remote.response.khoa.KhoaResponse;

import io.reactivex.Single;

public interface KhoaDataSource {
    interface LocalDataSource{

    }
    interface RemoteDataSource{
        Single<KhoaResponse> listKhoa(String token);
    }
}
