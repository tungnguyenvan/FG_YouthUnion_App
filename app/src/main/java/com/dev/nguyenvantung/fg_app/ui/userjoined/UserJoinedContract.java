package com.dev.nguyenvantung.fg_app.ui.userjoined;


import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.dev.nguyenvantung.fg_app.data.model.userhoatdong.UserHoatDong;

import java.util.List;

public interface UserJoinedContract {
    interface View{
        void showProgress();
        void dimissProgress();
        void setListUser(List<UserHoatDong> userHoatDongs);
        void showUser(int id);
    }
    interface Presenter{
        void setView(View mView);
        void listUserJoined(String token, int id);
    }
}
