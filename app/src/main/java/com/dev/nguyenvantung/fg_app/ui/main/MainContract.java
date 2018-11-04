package com.dev.nguyenvantung.fg_app.ui.main;

public interface MainContract {
    interface View {
        void showProgress();
        void dimissProgress();
        void logOutSuccess();
        void logOutFail();
    }
    interface Presenter {
        void setView(View mView);
        void logOut(String token);
    }
}
