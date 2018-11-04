package com.dev.nguyenvantung.fg_app.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPref {
    private static AppPref instance;
    private static SharedPreferences preference;

    public AppPref(Context context){
        preference = context.getApplicationContext().getSharedPreferences(AppConstants.APP_PREF, Context.MODE_PRIVATE);
    }

    public static AppPref getInstance(Context context){
        if(preference == null){
            instance = new AppPref(context);
        }

        return instance;
    }

    public void putApiToken(String token){
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(AppConstants.API_TOKEN, token);
        editor.commit();
    }

    public String getApiToken(){
        return preference.getString(AppConstants.API_TOKEN, "");
    }

    public void putEmail(String email){
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(AppConstants.EMAIL, email);
        editor.commit();
    }

    public String getEmail(){
        return preference.getString(AppConstants.EMAIL, "");
    }

    public void putPassword(String password){
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(AppConstants.PASSWORD, password);
        editor.commit();
    }

    public String getPassword(){
        return preference.getString(AppConstants.PASSWORD, "");
    }

    public void putRememberMe(boolean checked){
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(AppConstants.ISREMEMBER, checked);
        editor.commit();
    }

    public boolean getRemember(){
        return preference.getBoolean(AppConstants.ISREMEMBER, false);
    }
}
