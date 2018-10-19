package com.dev.nguyenvantung.fg_app.data.source.local;

import com.dev.nguyenvantung.fg_app.data.source.LCDoanDetailDataSource;

public class LCDoanDetailLocalDataSource implements LCDoanDetailDataSource.LocalDataSource{
    private static LCDoanDetailLocalDataSource instance;

    private LCDoanDetailLocalDataSource() {

    }

    public static LCDoanDetailLocalDataSource getInstance() {
       if (instance == null) {
           instance = new LCDoanDetailLocalDataSource();
       }
       return instance;
    }
}
