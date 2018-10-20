package com.dev.nguyenvantung.fg_app.ui.storehoatdong;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.model.hoatdongtype.HoatDongType;

import java.util.List;

public interface StoreHoatDongConstact {
    interface View{
        void showProgressbar();
        void dimissProgressbar();
    }
    interface Presenter{
        void storeHoatDong(HoatDong token);
        void setView(View mView);
    }
}
