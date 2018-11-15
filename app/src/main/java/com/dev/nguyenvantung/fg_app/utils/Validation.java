package com.dev.nguyenvantung.fg_app.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDongRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Validation {
    public Validation() {
    }

    public boolean checkEmail(String email){
        return (TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public boolean checkPassword(String password){
        return (password.length() > 8);
    }

    public boolean checkStoreDataHoatDong(Context context, HoatDongRequest mHoatDongRequest,
                                          EditText edName, TextView txtFrom, TextView txtEnd,
                                          Spinner spType, EditText edDesc){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (mHoatDongRequest.getName() == null || mHoatDongRequest.getName().equals("")){
            edName.setError(context.getString(R.string.required));
            return false;
        }
        if (mHoatDongRequest.getHoatDongTypeID() == null) {
            ((TextView) spType.getSelectedView()).setError(context.getString(R.string.required));
            return false;
        }
        if (mHoatDongRequest.getDesc() == null || mHoatDongRequest.getDesc().equals("")){
            edDesc.setError(context.getString(R.string.required));
            return false;
        }

        try {
            if (simpleDateFormat.parse(mHoatDongRequest.getFrom())
                    .equals(simpleDateFormat.parse(mHoatDongRequest.getEnd())) ||
                    simpleDateFormat.parse(mHoatDongRequest.getEnd())
                            .before(simpleDateFormat.parse(mHoatDongRequest.getFrom()))){
                txtEnd.setError("");
                txtFrom.setError("");
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }
}
