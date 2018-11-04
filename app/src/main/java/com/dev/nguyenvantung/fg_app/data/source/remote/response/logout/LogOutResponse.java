package com.dev.nguyenvantung.fg_app.data.source.remote.response.logout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogOutResponse {
    @Expose
    @SerializedName("message")
    private String message = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
