package com.dev.nguyenvantung.fg_app.ui.main.fragment.coming;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;

import java.util.List;

public interface HoatDongComingConstract {

    interface View{
        void setData(List<HoatDong> listHoatdong);
    }
}
