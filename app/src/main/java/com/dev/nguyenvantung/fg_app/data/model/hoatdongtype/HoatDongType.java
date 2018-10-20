package com.dev.nguyenvantung.fg_app.data.model.hoatdongtype;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HoatDongType {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
