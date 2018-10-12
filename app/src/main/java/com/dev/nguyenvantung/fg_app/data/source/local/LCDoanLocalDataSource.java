package com.dev.nguyenvantung.fg_app.data.source.local;

import com.dev.nguyenvantung.fg_app.data.source.LCDoanDataSource;

public class LCDoanLocalDataSource implements LCDoanDataSource.LocalDataSource {
    private static LCDoanLocalDataSource instance;

    private LCDoanLocalDataSource() {
    }

    public static LCDoanLocalDataSource getInstance(){
        if (instance == null){
            instance = new LCDoanLocalDataSource();
        }
        return instance;
    }
}
