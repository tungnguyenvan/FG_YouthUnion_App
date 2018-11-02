package com.dev.nguyenvantung.fg_app.ui.lcdoan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.khoa.Khoa;
import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;
import com.dev.nguyenvantung.fg_app.data.repository.KhoaRepositoty;
import com.dev.nguyenvantung.fg_app.data.repository.LCDoanRepository;
import com.dev.nguyenvantung.fg_app.data.source.local.KhoaLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.local.LCDoanLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.KhoaRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.LCDoanRemoteDataSource;
import com.dev.nguyenvantung.fg_app.ui.lcdoan.adapter.LCDoanAdapter;
import com.dev.nguyenvantung.fg_app.ui.lcdoan.adapter.LCDoanSpinnerAdapter;
import com.dev.nguyenvantung.fg_app.ui.lcdoandetail.LCDoanDetailActivity;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.navigator.Navigator;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LCDoanFragment extends Fragment implements LCDoanConstact.View{
    private static final String TAG = LCDoanFragment.class.getName();
    private static LCDoanFragment instance;
    private View view;
    @BindView(R.id.rv_lcdoan)
    public RecyclerView rv_lcdoan;
    @BindView(R.id.lcdoan_sp)
    public Spinner spinner;
    private LCDoanAdapter lcDoanAdapter;
    private List<LCDoan> listLCDoan;
    private List<Khoa> listKhoa;
    private LCDoanSpinnerAdapter spinnerAdapter;
    private LCDoanConstact.Presenter mPresenter;
    private LCDoanConstact.View mView;
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
        initSpinner();

        LCDoanRepository lcDoanRepository = new LCDoanRepository(LCDoanLocalDataSource.getInstance(),
                LCDoanRemoteDataSource.getInstance(getContext()));
        KhoaRepositoty khoaRepositoty = new KhoaRepositoty(KhoaLocalDataSource.getInstance(),
                KhoaRemoteDataSource.getInstance(getContext()));

        mPresenter = new LCDoanPresenter(lcDoanRepository, khoaRepositoty, SchedulerProvider.getInstance());
        mPresenter.listLCDoan(AppConstants.BEARER + AppPref.getInstance(getContext()).getApiToken());
        mPresenter.listKhoa(AppConstants.BEARER + AppPref.getInstance(getContext()).getApiToken());

        mPresenter.setView(this);

        return view;
    }

    private void initSpinner() {
        listKhoa = new ArrayList<Khoa>();

        spinnerAdapter = new LCDoanSpinnerAdapter(getContext(), listKhoa);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String id = listKhoa.get(i).toString();
//                mPresenter.ListLCDoanById(AppConstants.BEARER + AppPref.getInstance(getContext()).getApiToken(), id);
                Log.d(TAG, id + "-");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
    public void setListKhoa(List<Khoa> listKhoa) {
        this.listKhoa.addAll(listKhoa);
        spinnerAdapter.notifyDataSetChanged();
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
