package com.dev.nguyenvantung.fg_app.data.source.local;

import com.dev.nguyenvantung.fg_app.data.source.HoatDongTypeDataSource;

public class HoatDongTypeLocalDataSource implements HoatDongTypeDataSource.LocalDataSource {
    private static HoatDongTypeLocalDataSource instance;

    public HoatDongTypeLocalDataSource(){

    }

    public static synchronized HoatDongTypeLocalDataSource getInstance(){
        if (instance == null){
            instance = new HoatDongTypeLocalDataSource();
        }

        return instance;
    }
}
