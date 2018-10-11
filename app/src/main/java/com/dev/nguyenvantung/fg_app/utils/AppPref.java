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
}
