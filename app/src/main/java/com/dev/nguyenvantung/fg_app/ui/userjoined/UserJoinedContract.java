package com.dev.nguyenvantung.fg_app.ui.userjoined;


import com.dev.nguyenvantung.fg_app.data.model.user.User;

import java.util.List;

public interface UserJoinedContract {
    interface View{
        void showProgress();
        void dimissProgress();
        void setListUser(List<User> users);
    }
    interface Presenter{
        void setView(View mView);
        void listUserJoined(String token, int id);
    }
}
