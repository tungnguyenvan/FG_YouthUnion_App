package com.dev.nguyenvantung.fg_app.ui.lcdoan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;
import com.dev.nguyenvantung.fg_app.data.repository.LCDoanRepository;
import com.dev.nguyenvantung.fg_app.data.source.local.LCDoanLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.LCDoanRemoteDataSource;
import com.dev.nguyenvantung.fg_app.ui.lcdoan.adapter.LCDoanAdapter;
import com.dev.nguyenvantung.fg_app.ui.lcdoandetail.LCDoanDetailActivity;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.navigator.Navigator;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LCDoanFragment extends Fragment implements LCDoanConstact.View{
    private static LCDoanFragment instance;
    private View view;
    @BindView(R.id.rv_lcdoan)
    public RecyclerView rv_lcdoan;

    private LCDoanAdapter lcDoanAdapter;
    private List<LCDoan> listLCDoan;
    private LCDoanConstact.Presenter mPresenter;

    public LCDoanFragment() {

    }

    public static LCDoanFragment getInstance() {
        if (instance == null){
            instance = new LCDoanFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lcdoan, container, false);
        ButterKnife.bind(this, view);

        initRecyclerView();

        LCDoanRepository lcDoanRepository = new LCDoanRepository(LCDoanLocalDataSource.getInstance(),
                LCDoanRemoteDataSource.getInstance(getContext()));

        mPresenter = new LCDoanPresenter(lcDoanRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);
        mPresenter.listLCDoan(AppConstants.BEARER + AppPref.getInstance(getContext()).getApiToken());

        return view;
    }

    private void initRecyclerView() {
        listLCDoan = new ArrayList<>();
        lcDoanAdapter = new LCDoanAdapter(listLCDoan, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        rv_lcdoan.setHasFixedSize(true);
        rv_lcdoan.setLayoutManager(linearLayoutManager);
        rv_lcdoan.setAdapter(lcDoanAdapter);
    }

    @Override
    public void setListLCDoan(List<LCDoan> listLCDoan) {
        this.listLCDoan.addAll(listLCDoan);
        lcDoanAdapter.notifyDataSetChanged();
    }

    @Override
    public void nextPage(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt(AppConstants.ID_LCD, id);
        Navigator navigator = new Navigator(this);
        navigator.startActivity(LCDoanDetailActivity.class, bundle);
    }
}
