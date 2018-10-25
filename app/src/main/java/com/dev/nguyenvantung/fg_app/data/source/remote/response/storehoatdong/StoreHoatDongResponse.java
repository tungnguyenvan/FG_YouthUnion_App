package com.dev.nguyenvantung.fg_app.data.source.remote.response.storehoatdong;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreHoatDongResponse {
    @Expose
    @SerializedName("data")
    private HoatDong data = null;

    public HoatDong getData() {
        return data;
    }

    public void setData(HoatDong data) {
        this.data = data;
    }
}
