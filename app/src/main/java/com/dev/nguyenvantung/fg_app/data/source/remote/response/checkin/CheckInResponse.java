package com.dev.nguyenvantung.fg_app.data.source.remote.response.checkin;

import com.dev.nguyenvantung.fg_app.data.model.checkin.CheckIn;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckInResponse{
    @SerializedName("data")
    @Expose
    private CheckIn data;

    public CheckIn getData() {
        return data;
    }

    public void setData(CheckIn data) {
        this.data = data;
    }
}
