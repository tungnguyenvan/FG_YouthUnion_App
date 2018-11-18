package com.dev.nguyenvantung.fg_app.data.source.remote.response.user;

import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @Expose
    @SerializedName("data")
    private User data = null;

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
