package com.dev.nguyenvantung.fg_app.data.source.local;

public class LCDoanDetailLocalDataSource {
    private static LCDoanDetailLocalDataSource instance;

    private LCDoanDetailLocalDataSource() {

    }

    private LCDoanDetailLocalDataSource getInstance() {
       if (instance == null) {
           instance = new LCDoanDetailLocalDataSource();
       }
       return instance;
    }
}
