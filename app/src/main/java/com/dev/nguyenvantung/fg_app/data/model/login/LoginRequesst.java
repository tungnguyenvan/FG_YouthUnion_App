package com.dev.nguyenvantung.fg_app.data.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginRequesst implements Serializable {
    @Expose
    @SerializedName("email")
    private String email;
    @Expose
    @SerializedName("password")
    private String password;
    @Expose
    @SerializedName("remenber_me")
    private boolean remember_me;

    public String getEmail() {
        return email;
    }

    public LoginRequesst(String email, String password, boolean remember_me) {
        this.email = email;
        this.password = password;
        this.remember_me = remember_me;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember_me() {
        return remember_me;
    }

    public void setRemember_me(boolean remember_me) {
        this.remember_me = remember_me;
    }
}
