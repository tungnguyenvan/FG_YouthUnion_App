package com.dev.nguyenvantung.fg_app.data.source.remote.response.HoatDongType;

import com.dev.nguyenvantung.fg_app.data.model.hoatdongtype.HoatDongType;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HoatDongTypeReponse extends BaseResponse {
    @SerializedName("data")
    @Expose
    List<HoatDongType> data = null;

    public List<HoatDongType> getData() {
        return data;
    }

    public void setData(List<HoatDongType> data) {
        this.data = data;
    }
}
