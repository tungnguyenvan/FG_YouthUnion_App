package com.dev.nguyenvantung.fg_app.ui.lcdoandetail;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;
import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.dev.nguyenvantung.fg_app.data.repository.LCDoanRepository;
import com.dev.nguyenvantung.fg_app.data.repository.UserRepository;
import com.dev.nguyenvantung.fg_app.data.source.local.LCDoanLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.local.UserLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.LCDoanRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.UserRemoteDataSource;
import com.dev.nguyenvantung.fg_app.ui.lcdoandetail.adapter.LCDoanDetailAdapter;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LCDoanDetailActivity extends AppCompatActivity implements LCDoanDetailConstract.View{
    private static final String TAG = LCDoanDetailActivity.class.getName();
    @BindView(R.id.lcdoan_detail_txt_name)
    protected TextView txt_name;
    @BindView(R.id.lcdoan_detail_txt_desc)
    protected TextView txt_desc;
    @BindView(R.id.lcdoan_detail_sp_filter)
    protected Spinner Filter;
    @BindView(R.id.lcdoan_detail_rv_user)
    protected   RecyclerView recyclerView;

    private ProgressDialog progressDialog;
    private LCDoanDetailConstract.Presenter mPresenter;
    private LCDoanDetailAdapter adapter;
    private List<User> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lcdoan_detail);
        ButterKnife.bind(this);

        initRecyclerView();

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();
        int id = bundle.getInt(AppConstants.ID_LCD);
        Log.d(TAG, id + "");
        LCDoanRepository lcDoanRepository = new LCDoanRepository(LCDoanLocalDataSource.getInstance(), LCDoanRemoteDataSource.getInstance(this));
        UserRepository userRepository = new UserRepository(UserLocalDataSource.getInstance(), UserRemoteDataSource.getInstance(this));

        mPresenter = new LCDoanDetailPresenter(userRepository, lcDoanRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);
        mPresenter.getLCDoan(AppConstants.BEARER + AppPref.getInstance(this).getApiToken(), id);
        mPresenter.listUserLCDoan(AppConstants.BEARER + AppPref.getInstance(this).getApiToken(), id);
    }

    private void initRecyclerView() {
        listUser = new ArrayList<>();
        adapter = new LCDoanDetailAdapter(listUser);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initPrgressbarDialog(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đang xử lí");
        progressDialog.setMessage(getResources().getString(R.string.messageConnectToServe));
    }

    @Override
    public void showProgressbar() {
        if (progressDialog == null){
            initPrgressbarDialog();
        }

        progressDialog.show();
    }

    @Override
    public void dimissProgressbar() {
        progressDialog.dismiss();
    }

    @Override
    public void setListUser(List<User> listUser) {
        Log.d(TAG, listUser.get(0).toString());
        this.listUser.addAll(listUser);
    }


    @Override
    public void setLCDoan(LCDoan lcDoan) {
        txt_name.setText(lcDoan.getName());
        txt_desc.setText(lcDoan.getDesc());
    }
}
