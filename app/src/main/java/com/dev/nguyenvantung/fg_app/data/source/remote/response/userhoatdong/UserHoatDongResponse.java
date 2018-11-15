package com.dev.nguyenvantung.fg_app.data.source.remote.response.userhoatdong;

import com.dev.nguyenvantung.fg_app.data.model.userhoatdong.UserHoatDong;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserHoatDongResponse extends BaseResponse {

    @Expose
    @SerializedName("data")
    private List<UserHoatDong> data = null;

    public List<UserHoatDong> getData() {
        return data;
    }

    public void setData(List<UserHoatDong> data) {
        this.data = data;
    }
}
