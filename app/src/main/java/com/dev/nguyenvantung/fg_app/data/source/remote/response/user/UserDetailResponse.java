package com.dev.nguyenvantung.fg_app.data.source.remote.response.user;

import com.dev.nguyenvantung.fg_app.data.model.userdetail.UserDetail;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetailResponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    private UserDetail data;

    public UserDetail getData() {
        return data;
    }

    public void setData(UserDetail data) {
        this.data = data;
    }

}

