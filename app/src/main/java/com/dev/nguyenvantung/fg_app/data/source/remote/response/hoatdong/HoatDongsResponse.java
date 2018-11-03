package com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong;


import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HoatDongsResponse extends BaseResponse{
    @SerializedName("data")
    @Expose
    private List<HoatDong> data = null;
    public List<HoatDong> getData() {
        return data;
    }

    public void setData(List<HoatDong> data) {
        this.data = data;
    }

}
