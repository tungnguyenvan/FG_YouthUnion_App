package com.dev.nguyenvantung.fg_app.ui.user;

import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserDetailResponse;

public interface UserConstact {
    interface View{
        void setUserDetail(UserDetailResponse data);
        void showProgressBar();
        void dismissProgressBar();
    }
    interface Presenter{
        void setView(View view);
        void userDetail(String token, int id);
    }
}
