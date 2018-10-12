package com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoan;

import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LCDoanResponse {
    @SerializedName("data")
    @Expose
    private List<LCDoan> data = null;

    public List<LCDoan> getData() {
        return data;
    }

    public void setData(List<LCDoan> data) {
        this.data = data;
    }
}
