package com.dev.nguyenvantung.fg_app.ui.lcdoan;

import com.dev.nguyenvantung.fg_app.data.model.khoa.Khoa;
import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;

import java.util.List;

import io.reactivex.Single;

public interface LCDoanConstact {
    interface View{
        void setListKhoa(List<Khoa> listKhoa);
        void setListLCDoan(List<LCDoan> listLCDoan);
        void nextPage(int id);
        void listLCDoanById(List<LCDoan> listLCDoan);
    }
    interface Presenter{
        void ListLCDoanById(String token, int id);
        void listKhoa(String token);
        void listLCDoan(String token);
        void setView(LCDoanConstact.View mView);
    }
}
