package com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoandetail;

import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LCDoanDetailResponse {

    @SerializedName("data")
    @Expose
    private LCDoan lcDoan;

    public LCDoan getLcDoan() {
        return lcDoan;
    }

    public void setLcDoan(LCDoan lcDoan) {
        this.lcDoan = lcDoan;
    }


}
