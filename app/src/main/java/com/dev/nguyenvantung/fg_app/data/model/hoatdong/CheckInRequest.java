package com.dev.nguyenvantung.fg_app.data.model.hoatdong;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckInRequest {
    @Expose
    @SerializedName("user_id")
    private int userId;

    @Expose
    @SerializedName("hoatdong_id")
    private int hoatDongId;

    public CheckInRequest(int userId, int hoatDongId) {
        this.userId = userId;
        this.hoatDongId = hoatDongId;
    }
}
