package com.dev.nguyenvantung.fg_app.ui.lcdoan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;
import com.dev.nguyenvantung.fg_app.data.repository.LCDoanRepository;
import com.dev.nguyenvantung.fg_app.data.source.LCDoanDataSource;
import com.dev.nguyenvantung.fg_app.data.source.local.LCDoanLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.LCDoanRemoteDataSource;
import com.dev.nguyenvantung.fg_app.ui.lcdoan.adapter.LCDoanAdapter;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LCDoanActivity extends AppCompatActivity implements LCDoanConstact.View {
    @BindView(R.id.rv_lcdoan)
    public RecyclerView rv_lcdoan;

    private LCDoanAdapter lcDoanAdapter;
    private List<LCDoan> listLCDoan;
    private LCDoanConstact.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lcdoan);
        ButterKnife.bind(this);

        initRecyclerView();
        AppPref.getInstance(this).putApiToken(AppConstants.TOKEN);
        LCDoanRepository lcDoanRepository = new LCDoanRepository(LCDoanLocalDataSource.getInstance(),
                LCDoanRemoteDataSource.getInstance(this));

        mPresenter = new LCDoanPresenter(lcDoanRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);
        mPresenter.listLCDoan(AppConstants.BEARER + AppPref.getInstance(this).getApiToken());
    }

    private void initRecyclerView() {
        listLCDoan = new ArrayList<>();
        lcDoanAdapter = new LCDoanAdapter(listLCDoan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),
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
}
