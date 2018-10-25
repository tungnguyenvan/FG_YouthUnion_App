package com.dev.nguyenvantung.fg_app.data.model.hoatdong;

import com.dev.nguyenvantung.fg_app.data.model.base.BaseModel;
import com.dev.nguyenvantung.fg_app.data.model.hoatdongtype.HoatDongType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreHoatDong {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("from_date")
    @Expose
    private String fromDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("hoc_ky")
    @Expose
    private Object hocKy;
    @SerializedName("hoatdong_type")
    @Expose
    private HoatDongType hoatdongType;


}
