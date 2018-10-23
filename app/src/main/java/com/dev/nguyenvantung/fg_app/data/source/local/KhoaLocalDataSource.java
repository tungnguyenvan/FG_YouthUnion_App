package com.dev.nguyenvantung.fg_app.data.source.local;

import com.dev.nguyenvantung.fg_app.data.source.KhoaDataSource;

public class KhoaLocalDataSource implements KhoaDataSource.LocalDataSource{
    private static KhoaLocalDataSource instance;

    public KhoaLocalDataSource() {

    }

    public static KhoaLocalDataSource getInstance() {
        if (instance == null) {
            instance = new KhoaLocalDataSource();
        }
        return instance;
    }
}
