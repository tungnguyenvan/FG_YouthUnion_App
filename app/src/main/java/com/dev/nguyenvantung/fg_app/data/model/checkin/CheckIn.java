package com.dev.nguyenvantung.fg_app.data.model.checkin;

import com.dev.nguyenvantung.fg_app.data.model.base.BaseModel;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckIn extends BaseModel {
    @Expose
    @SerializedName("user")
    private User user;

    @Expose
    @SerializedName("hoat_dong")
    private HoatDong hoatDong;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HoatDong getHoatDong() {
        return hoatDong;
    }

    public void setHoatDong(HoatDong hoatDong) {
        this.hoatDong = hoatDong;
    }
}
