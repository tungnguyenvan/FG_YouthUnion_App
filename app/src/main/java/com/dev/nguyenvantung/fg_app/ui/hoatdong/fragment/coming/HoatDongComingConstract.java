package com.dev.nguyenvantung.fg_app.ui.hoatdong.fragment.coming;


import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;

import java.util.List;

public interface HoatDongComingConstract {
    interface View{
        void setListHoatDongComing(List<HoatDong> listHoatDongComing);
        void showProgressBar();
        void dismissProgressBar();
        void hoatDongDetail(android.view.View view, int id);
        void setHoatDong(HoatDong mHoatDong);
    }

    interface Presenter{
        void setView(HoatDongComingConstract.View view);
        void listHoatDongComing(String token);
        void hoatDong(String token, int id);
    }
}
