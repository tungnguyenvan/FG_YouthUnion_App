package com.dev.nguyenvantung.fg_app.ui.lcdoan;

import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;

import java.util.List;

import io.reactivex.Single;

public interface LCDoanConstact {
    interface View{
        void setListLCDoan(List<LCDoan> listLCDoan);
        void nextPage(int id);
    }
    interface Presenter{
        void listLCDoan(String token);
        void setView(LCDoanConstact.View mView);
    }
}
