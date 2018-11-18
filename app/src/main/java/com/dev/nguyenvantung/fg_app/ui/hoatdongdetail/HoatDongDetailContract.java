package com.dev.nguyenvantung.fg_app.ui.hoatdongdetail;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.CheckInRequest;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.checkin.CheckInResponse;

import java.util.List;

public interface HoatDongDetailContract {
    interface View{
        void showProgressbar();
        void dimissProgresbar();
        void setHoatDong(HoatDong hoatDong);
        void setListUser(List<User> listUser);
        void checkIn(int possition);
        void checkInSuccess(CheckInResponse checkInResponse, int possition);
        void showUser(int id);
    }
    interface Presenter{
        void setView(View mView);
        void getHoatDong(String token, int id);
        void listUserNotJoin(String token, int id);
        void checkIn(String token, CheckInRequest checkInRequest, int possition);
    }
}
