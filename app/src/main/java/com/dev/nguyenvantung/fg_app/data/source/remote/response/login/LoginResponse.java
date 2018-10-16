package com.dev.nguyenvantung.fg_app.data.source.remote.response.login;

import com.dev.nguyenvantung.fg_app.data.model.base.BaseModel;
import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse extends BaseModel {
    @Expose
    @SerializedName("access_token")
    private String access_token;
    @Expose
    @SerializedName("token_type")
    private String token_type;
    @Expose
    @SerializedName("expires_at")
    private String expires_at;
    @Expose
    @SerializedName("user")
    private User user;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
