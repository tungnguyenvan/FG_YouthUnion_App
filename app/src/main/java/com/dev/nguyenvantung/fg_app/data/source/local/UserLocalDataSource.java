package com.dev.nguyenvantung.fg_app.data.source.local;

import com.dev.nguyenvantung.fg_app.data.source.UserDataSource;

public class UserLocalDataSource implements UserDataSource.LocalDataSource{
    private static UserLocalDataSource instance;

    private UserLocalDataSource(){

    }

    public static UserLocalDataSource getInstance(){
        if (instance == null){
            instance = new UserLocalDataSource();
        }
        return instance;
    }
}
