package com.dev.nguyenvantung.fg_app.data.source.remote.service;

import android.content.Context;

import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiKhoa;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiHoatDongType;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiLCDoan;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiHoatDong;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiLogin;
import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiUser;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;

public class AppServiceClient extends ServiceClient {
    private static ApiUser mApiUser;
    private static ApiLCDoan mApiLCDoan;
    private static ApiHoatDong mApiHoatDong;
    private static ApiLogin mApiLogin;
    private static ApiKhoa mApiKhoa;
    private static ApiHoatDongType mApiHoatDongType;

    public static ApiKhoa getKhoaRemoteInstance(Context context) {
        if (mApiKhoa == null) {
            mApiKhoa = createService(context, AppConstants.END_POINT, ApiKhoa.class);
        }
        return mApiKhoa;
    }

    public static ApiUser getUserRemoteInstance(Context context){
        if (mApiUser == null){
            mApiUser = createService(context, AppConstants.END_POINT, ApiUser.class);
        }
        return mApiUser;
    }

    public static ApiLCDoan getLCDoanRemoteInstance(Context context) {
        if (mApiLCDoan == null) {
            mApiLCDoan = createService(context, AppConstants.END_POINT, ApiLCDoan.class);
        }
        return mApiLCDoan;
    }
    public static ApiHoatDong getHoatDongRemoteInstance (Context context){
        if (mApiHoatDong == null) {
            mApiHoatDong = createService(context, AppConstants.END_POINT, ApiHoatDong.class);
        }
        return mApiHoatDong;
    }

    public static ApiLogin getLoginRemoteInstance(Context context){
        if (mApiLogin == null){
            mApiLogin = createService(context, AppConstants.END_POINT, ApiLogin.class);
        }
        return mApiLogin;
    }

    public static ApiHoatDongType getHoatDongTypeRemoteInstance(Context context){
        if (mApiHoatDongType == null){
            mApiHoatDongType = createService(context, AppConstants.END_POINT, ApiHoatDongType.class);
        }
        return mApiHoatDongType;
    }
}
