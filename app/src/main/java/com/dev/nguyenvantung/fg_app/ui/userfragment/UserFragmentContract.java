package com.dev.nguyenvantung.fg_app.ui.userfragment;

import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.dev.nguyenvantung.fg_app.data.model.userhoatdong.UserHoatDong;

import java.util.List;

public interface UserFragmentContract {
    interface View {
        void showProgress();
        void dimissProgress();
        void setDataUser(User user);
        void setDataHoatDong(List<UserHoatDong> mUserHoatDongs);
    }
    interface Presenter {
        void setView(View mView);
        void getUser(String token, int id);
        void getHoatDongJoined(String token, int id);
    }
}