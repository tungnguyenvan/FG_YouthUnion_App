package com.dev.nguyenvantung.fg_app.ui.main.fragment.finished;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.repository.HoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.source.local.HoatDongLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.HoatDongRemoteDataSource;
import com.dev.nguyenvantung.fg_app.ui.main.fragment.adapter.HoatDongAdapter;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HoatDongFinishedFragment extends Fragment implements HoatDongFinishedConstract.View{
    private static HoatDongFinishedFragment instance;
    private static final String TAG = "HoatDongFinishedFragment: ";
    private HoatDongFinishedConstract.Presenter mPresenter;
    // RecycleView
    @BindView(R.id.rv_hoatdong_end)
    protected RecyclerView rv_hoatdong_end;
    @BindView(R.id.end_progress)
    protected ProgressBar progressBar;

    private HoatDongAdapter hoatDongAdapter;
    private List<HoatDong> listHoatDong;

    public HoatDongFinishedFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance(){
        if (instance == null){
            instance = new HoatDongFinishedFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finished, container, false);
        ButterKnife.bind(this, view);
        initRecyclerView();

        HoatDongRepository hoatDongRepository = new HoatDongRepository(HoatDongLocalDataSource.getInstance(),
                HoatDongRemoteDataSource.getInstance(getContext()));
        mPresenter = new HoatDongFinishedPresenter(hoatDongRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);
        mPresenter.listHoatDongFinished(AppConstants.BEARER + AppPref.getInstance(getContext()).getApiToken());

        return view;
    }

    private void initRecyclerView() {
        listHoatDong = new ArrayList<>();
        hoatDongAdapter = new HoatDongAdapter(listHoatDong, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        rv_hoatdong_end.setHasFixedSize(true);
        rv_hoatdong_end.setLayoutManager(linearLayoutManager);
        rv_hoatdong_end.setAdapter(hoatDongAdapter);
    }


    @Override
    public void setListHoatDongFinished(List<HoatDong> listHoatDong) {
        this.listHoatDong.addAll(listHoatDong);
        hoatDongAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
