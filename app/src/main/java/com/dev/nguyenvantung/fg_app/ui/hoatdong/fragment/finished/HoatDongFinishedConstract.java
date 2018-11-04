package com.dev.nguyenvantung.fg_app.ui.hoatdong.fragment.finished;



import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;

import java.util.List;

public interface HoatDongFinishedConstract {
    interface View{
        void setListHoatDongFinished(List<HoatDong> listHoatDongFinished);
        void showProgressBar();
        void dismissProgressBar();
        void hoatDongDetail(android.view.View view, int id);
    }

    interface Presenter{
        void listHoatDongFinished(String token);
        void setView(HoatDongFinishedConstract.View view);
    }
}
