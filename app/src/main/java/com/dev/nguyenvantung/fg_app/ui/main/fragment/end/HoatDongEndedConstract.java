package com.dev.nguyenvantung.fg_app.ui.main.fragment.end;



import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;

import java.util.List;

public interface HoatDongEndedConstract {
    interface View{
        void setListHoatDong(List<HoatDong> listHoatDong);
    }

    interface Presenter{
        void listHoatDong(String token);
        void setView(HoatDongEndedConstract.View view);
    }
}
