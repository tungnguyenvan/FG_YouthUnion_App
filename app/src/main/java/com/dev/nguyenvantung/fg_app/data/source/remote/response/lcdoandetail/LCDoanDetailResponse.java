package com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoandetail;

import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LCDoanDetailResponse {
    @SerializedName("data")
    @Expose
    List<User> data = null;
    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
