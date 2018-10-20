package com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoandetail;

import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;
import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LCDoanDetailResponse {

    private UserResponse listUser;

    @SerializedName("data")
    @Expose
    private LCDoan lcDoan;

    public UserResponse getListUser() {
        return listUser;
    }

    public void setListUser(UserResponse listUser) {
        this.listUser = listUser;
    }

    public LCDoan getLcDoan() {
        return lcDoan;
    }

    public void setLcDoan(LCDoan lcDoan) {
        this.lcDoan = lcDoan;
    }


}
