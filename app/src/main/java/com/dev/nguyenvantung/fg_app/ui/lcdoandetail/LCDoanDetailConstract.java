package com.dev.nguyenvantung.fg_app.ui.lcdoandetail;

import com.dev.nguyenvantung.fg_app.data.model.user.User;

import java.util.List;

public interface LCDoanDetailConstract {
    interface View {
        void setListUser(List<User> listUser);
    }

    interface Presenter {
        void ListUser(String token);
        void setView(LCDoanDetailConstract.View mView);
    }
}
