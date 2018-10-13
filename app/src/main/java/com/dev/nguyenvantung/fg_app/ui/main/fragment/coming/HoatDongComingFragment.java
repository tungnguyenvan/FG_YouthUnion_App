package com.dev.nguyenvantung.fg_app.ui.main.fragment.coming;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.nguyenvantung.fg_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HoatDongComingFragment extends Fragment {
    private static HoatDongComingFragment instance;

    public HoatDongComingFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance(){
        if (instance == null){
            instance = new HoatDongComingFragment();
        }
        return new HoatDongComingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_coming, container, false);
        return view;
    }

}
