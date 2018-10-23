package com.dev.nguyenvantung.fg_app.data.source.remote.response.khoa;

import com.dev.nguyenvantung.fg_app.data.model.khoa.Khoa;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KhoaResponse {
    @SerializedName("data")
    @Expose
    private List<Khoa> listKhoa = null;

    public List<Khoa> getListKhoa() {
        return listKhoa;
    }

    public void setListKhoa(List<Khoa> listKhoa) {
        this.listKhoa = listKhoa;
    }
}
