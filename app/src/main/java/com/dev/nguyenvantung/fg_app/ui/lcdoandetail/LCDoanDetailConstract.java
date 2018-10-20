package com.dev.nguyenvantung.fg_app.ui.lcdoandetail;

import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;
import com.dev.nguyenvantung.fg_app.data.model.user.User;

import java.util.List;

public interface LCDoanDetailConstract {
    interface View {
        void showProgressbar();
        void dimissProgressbar();
        void setListUser(List<User> listUser);
        void setLCDoan(LCDoan lcDoan);
    }

    interface Presenter {
        void getLCDoan(String token, int id);
        void listUser(String token);
        void setView(LCDoanDetailConstract.View mView);
    }
}
