package com.dev.nguyenvantung.fg_app.data.source.local;

import com.dev.nguyenvantung.fg_app.data.source.StoreHoatDongDataSource;

public class StoreHoatDongLocalDataSource implements StoreHoatDongDataSource.LocalDataSource {
    private static StoreHoatDongLocalDataSource instance;

    public StoreHoatDongLocalDataSource(){

    }
    public static synchronized StoreHoatDongLocalDataSource getInstance(){
        if (instance == null){
            instance = new StoreHoatDongLocalDataSource();
        }
        return instance;
    }
}
