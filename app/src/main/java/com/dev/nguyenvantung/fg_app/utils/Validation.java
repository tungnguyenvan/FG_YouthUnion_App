package com.dev.nguyenvantung.fg_app.utils;

import android.text.TextUtils;
import android.util.Patterns;

public class Validation {
    public Validation() {
    }

    public boolean checkEmail(String email){
        return (TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public boolean checkPassword(String password){
        return (password.length() > 8);
    }
}
