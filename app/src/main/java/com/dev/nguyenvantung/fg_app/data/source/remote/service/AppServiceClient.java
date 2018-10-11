package com.dev.nguyenvantung.fg_app.data.source.remote.service;

import android.content.Context;

import com.dev.nguyenvantung.fg_app.data.source.remote.api.ApiUser;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;

public class AppServiceClient extends ServiceClient {
    private static ApiUser mApiUser;

    public static ApiUser getUserRemoteInstance(Context context){
        if (mApiUser == null){
            mApiUser = createService(context, AppConstants.END_POINT, ApiUser.class);
        }
        return mApiUser;
    }
}
