package com.dev.nguyenvantung.fg_app.ui.user;

import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.dev.nguyenvantung.fg_app.data.model.userdetail.UserDetail;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;

import java.util.List;

public interface UserConstact {
    interface View{
        void setUserDetail(UserResponse data);
        void showProgressBar();
        void dismissProgressBar();
    }
    interface Presenter{
        void setView(View view);
        void userDetail(String token, int id);
    }
}
