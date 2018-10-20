package com.dev.nguyenvantung.fg_app.data.source;

import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoandetail.LCDoanDetailResponse;

import io.reactivex.Single;

public interface LCDoanDetailDataSource {
    interface LocalDataSource {

    }

    interface RemoteDataSource {
        Single<LCDoanDetailResponse> listUser(String token);
        Single<LCDoanDetailResponse> getLCDoan(String token, int id);
    }
}
