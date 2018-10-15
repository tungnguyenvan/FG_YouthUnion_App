package com.dev.nguyenvantung.fg_app.ui.main.fragment.coming;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.ui.main.fragment.adapter.HoatDongAdapter;
import com.dev.nguyenvantung.fg_app.ui.main.fragment.end.HoatDongEndedConstract;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HoatDongComingFragment extends Fragment implements com.dev.nguyenvantung.fg_app.ui.main.fragment.coming.HoatDongComingConstract.View {
    private static HoatDongComingFragment instance;
    private static final String TAG = "HoatDongComing: ";
    private HoatDongEndedConstract.Presenter mPresenter;
    @BindView(R.id.rv_hoatdong_coming)
    protected RecyclerView rv_hoatdong_end;

    private static com.dev.nguyenvantung.fg_app.ui.main.fragment.coming.HoatDongComingConstract.View mView;

    private HoatDongAdapter hoatDongAdapter;
    private List<HoatDong> listHoatDong;
    public HoatDongComingFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance(){
        if (instance == null){
            instance = new HoatDongComingFragment();
        }
        return instance;
    }

    public static com.dev.nguyenvantung.fg_app.ui.main.fragment.coming.HoatDongComingConstract.View getMview() {
        if (mView == null){
            mView = new HoatDongComingFragment();
        }
        return mView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_coming, container, false);
        ButterKnife.bind(this,view);
        initRecycleview();
        return view;
    }

    private void initRecycleview() {
        listHoatDong = new ArrayList<>();
        hoatDongAdapter = new HoatDongAdapter(listHoatDong);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        rv_hoatdong_end.setHasFixedSize(true);
        rv_hoatdong_end.setLayoutManager(linearLayoutManager);
        rv_hoatdong_end.setAdapter(hoatDongAdapter);
    }

    @Override
    public void setData(List<HoatDong> listHoatdong) {
        if (this.listHoatDong == null) this.listHoatDong = new ArrayList<>();
        this.listHoatDong.addAll(listHoatdong);
        hoatDongAdapter.notifyDataSetChanged();
    }
}
