package com.dev.nguyenvantung.fg_app.ui.user;

import com.dev.nguyenvantung.fg_app.data.model.user.User;

import java.util.List;

public interface UserConstact {
    interface View{
        void setListUser(List<User> listUser);
    }
    interface Presenter{
        void listUser(String token);
        void setView(UserConstact.View view);
    }
}
