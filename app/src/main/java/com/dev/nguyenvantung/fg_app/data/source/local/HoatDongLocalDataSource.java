package com.dev.nguyenvantung.fg_app.data.source.local;

import com.dev.nguyenvantung.fg_app.data.source.HoatDongDataSource;

public class HoatDongLocalDataSource implements HoatDongDataSource.LocalDataSource{
    private static HoatDongLocalDataSource instance;

    private HoatDongLocalDataSource(){

    }

    public static HoatDongLocalDataSource getInstance(){
        if (instance == null){
            instance = new HoatDongLocalDataSource();
        }
        return instance;
    }
}
