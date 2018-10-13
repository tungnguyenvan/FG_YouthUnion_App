package com.dev.nguyenvantung.fg_app.data.model.hocky;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HocKy {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("hocky")
    @Expose
    private String hocky;
    @SerializedName("namhoc_id")
    @Expose
    private Integer namhocId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHocky() {
        return hocky;
    }

    public void setHocky(String hocky) {
        this.hocky = hocky;
    }

    public Integer getNamhocId() {
        return namhocId;
    }

    public void setNamhocId(Integer namhocId) {
        this.namhocId = namhocId;
    }

}
