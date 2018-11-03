package com.dev.nguyenvantung.fg_app.ui.hoatdongdetail;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.CheckInRequest;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.checkin.CheckInResponse;

import java.util.List;

public class HoatDongDetailContract {
    public interface View{
        void showProgressbar();
        void dimissProgresbar();
        void setHoatDong(HoatDong hoatDong);
        void setListUser(List<User> listUser);
        void checkIn(int possition);
        void checkInSuccess(CheckInResponse checkInResponse, int possition);
    }
    interface Presenter{
        void setView(View mView);
        void getHoatDong(String token, int id);
        void listUser(String token);
        void checkIn(String token, CheckInRequest checkInRequest, int possition);
    }
}
