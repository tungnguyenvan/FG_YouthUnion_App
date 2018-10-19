package com.dev.nguyenvantung.fg_app.ui.storehoatdong;

public interface StoreHoatDongConstact {
    interface View{
        void showProgressbar();
        void dimissProgressbar();
    }
    interface Presenter{
        void listHoatDongType(String token);
    }
}
