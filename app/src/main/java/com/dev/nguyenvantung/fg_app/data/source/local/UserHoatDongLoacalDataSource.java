package com.dev.nguyenvantung.fg_app.data.source.local;

import com.dev.nguyenvantung.fg_app.data.source.UserHoatDongDataSource;

public class UserHoatDongLoacalDataSource implements UserHoatDongDataSource.LocalDataSource {
    private static UserHoatDongLoacalDataSource instance;

    private UserHoatDongLoacalDataSource(){

    }

    public static synchronized UserHoatDongLoacalDataSource getInstance(){
        if (instance == null){
            instance = new UserHoatDongLoacalDataSource();
        }

        return instance;
    }

}
