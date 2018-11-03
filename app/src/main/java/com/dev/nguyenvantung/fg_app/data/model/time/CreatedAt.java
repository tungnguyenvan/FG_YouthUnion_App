package com.dev.nguyenvantung.fg_app.data.model.time;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CreatedAt {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("timezone_type")
    @Expose
    private Integer timezoneType;
    @SerializedName("timezone")
    @Expose
    private String timezone;

    public String getDate() {
        return date;
    }

    public Integer getTimezoneType() {
        return timezoneType;
    }

    public String getTimezone() {
        return timezone;
    }

}
