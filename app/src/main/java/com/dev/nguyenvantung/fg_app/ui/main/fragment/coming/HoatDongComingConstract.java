package com.dev.nguyenvantung.fg_app.ui.main.fragment.coming;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.ui.main.fragment.end.HoatDongEndedConstract;

import java.util.List;

public interface HoatDongComingConstract {
    interface View{
        void setListHoatDongComing(List<HoatDong> listHoatDongComing);
        void showProgressBar();
        void dismissProgressBar();
    }

    interface Presenter{
        void listHoatDongComing(String token);
        void setView(HoatDongComingConstract.View view);
    }
}
