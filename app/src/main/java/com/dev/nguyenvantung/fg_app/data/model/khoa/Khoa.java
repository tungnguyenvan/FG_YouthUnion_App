package com.dev.nguyenvantung.fg_app.data.model.khoa;

import com.dev.nguyenvantung.fg_app.data.model.base.BaseModel;
import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Khoa extends BaseModel {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("lcdoans_id")
    @Expose
    private LCDoan lcDoan;

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

    public LCDoan getLcDoan() {
        return lcDoan;
    }

    public void setLcDoan(LCDoan lcDoan) {
        this.lcDoan = lcDoan;
    }

    @Override
    public String toString() {
        return this.name + "---\n" + this.desc + "---\n" + this.lcDoan;
    }
}
