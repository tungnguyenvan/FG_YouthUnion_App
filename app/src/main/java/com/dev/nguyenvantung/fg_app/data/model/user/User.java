package com.dev.nguyenvantung.fg_app.data.model.user;

import com.dev.nguyenvantung.fg_app.data.model.base.BaseModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User extends BaseModel{
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("role_id")
    @Expose
    private Integer roleId;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


    @Override
    public String toString() {
        return this.username + "---" + this.email;
    }
}
