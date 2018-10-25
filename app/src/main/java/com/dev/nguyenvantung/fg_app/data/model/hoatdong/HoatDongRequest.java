package com.dev.nguyenvantung.fg_app.data.model.hoatdong;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class HoatDongRequest {
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("desc")
    private String desc;
    @Expose
    @SerializedName("from_date")
    private String from;
    @Expose
    @SerializedName("end_date")
    private String end;
    @Expose
    @SerializedName("hoatdong_type_id")
    private int hoatDongTypeID;

    public HoatDongRequest() {
    }

    public HoatDongRequest(String name, String desc, String from, String end, int hoatDongTypeID) {
        this.name = name;
        this.desc = desc;
        this.from = from;
        this.end = end;
        this.hoatDongTypeID = hoatDongTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getHoatDongTypeID() {
        return hoatDongTypeID;
    }

    public void setHoatDongTypeID(int hoatDongTypeID) {
        this.hoatDongTypeID = hoatDongTypeID;
    }
}
