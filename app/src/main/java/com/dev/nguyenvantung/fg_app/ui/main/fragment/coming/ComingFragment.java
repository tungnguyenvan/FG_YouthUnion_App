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
public class ComingFragment extends Fragment {
    private static ComingFragment instance;

    public ComingFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance(){
        if (instance == null){
            instance = new ComingFragment();
        }
        return new ComingFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coming, container, false);
    }

}
