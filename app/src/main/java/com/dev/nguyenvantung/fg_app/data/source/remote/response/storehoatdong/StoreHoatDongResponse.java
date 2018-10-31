package com.dev.nguyenvantung.fg_app.data.source.remote.response.storehoatdong;

import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.BaseResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreHoatDongResponse {
    @Expose
    @SerializedName("data")
    private Data data = null;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class UpdatedAt {

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

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getTimezoneType() {
            return timezoneType;
        }

        public void setTimezoneType(Integer timezoneType) {
            this.timezoneType = timezoneType;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }
    }

    public class Data {

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
        private Object hoatdongType;
        @SerializedName("updated_at")
        @Expose
        private UpdatedAt updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
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

        public String getFromDate() {
            return fromDate;
        }

        public void setFromDate(String fromDate) {
            this.fromDate = fromDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public Object getHocKy() {
            return hocKy;
        }

        public void setHocKy(Object hocKy) {
            this.hocKy = hocKy;
        }

        public Object getHoatdongType() {
            return hoatdongType;
        }

        public void setHoatdongType(Object hoatdongType) {
            this.hoatdongType = hoatdongType;
        }

        public UpdatedAt getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(UpdatedAt updatedAt) {
            this.updatedAt = updatedAt;
        }

    }
}
