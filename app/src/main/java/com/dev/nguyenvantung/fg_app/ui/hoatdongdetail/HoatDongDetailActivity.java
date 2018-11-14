package com.dev.nguyenvantung.fg_app.ui.hoatdongdetail;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.CheckInRequest;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.dev.nguyenvantung.fg_app.data.repository.HoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.repository.UserHoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.repository.UserRepository;
import com.dev.nguyenvantung.fg_app.data.source.local.HoatDongLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.local.UserHoatDongLoacalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.local.UserLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.HoatDongRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.UserHoatDongRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.UserRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.checkin.CheckInResponse;
import com.dev.nguyenvantung.fg_app.ui.hoatdongdetail.adapter.HoatDongDetailAdapter;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HoatDongDetailActivity extends AppCompatActivity implements HoatDongDetailContract.View {
    private static final String TAG = HoatDongDetailActivity.class.getName();

    @BindView(R.id.item_hoatdong_detail_txt_name)
    public TextView txt_name;

    @BindView(R.id.item_hoatdong_detail_txt_desc)
    public TextView txt_desc;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @BindView(R.id.hoatdong_detail_rv)
    public RecyclerView recyclerView;

    @BindView(R.id.hoatdong_detail_coming)
    public TextView txtComing;

    @BindView(R.id.hoatdong_detail_finish)
    public TextView txtFinish;

    @BindView(R.id.hoatdong_detail_img_edit)
    public ImageView imgEdit;

    @BindView(R.id.hoatdong_detail_btn_joined)
    public Button btnJoined;

    private HoatDongDetailContract.Presenter mPresenter;
    private AppPref appPref;

    private int idHoatDong;
    private List<User> users;
    private HoatDongDetailAdapter hoatDongDetailAdapter;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoat_dong_detail);
        ButterKnife.bind(this);

        getData();
        initView();
        initPresenter();
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        toolbar.setTitle(R.string.checkin);

        imgEdit.setOnClickListener(view -> editHoatDong());
        btnJoined.setOnClickListener(view -> joined());

        users = new ArrayList<>();
        hoatDongDetailAdapter = new HoatDongDetailAdapter(users, this);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(hoatDongDetailAdapter);
    }

    private void editHoatDong() {
        // TODO: 11/3/2018 Edit Hoat Dong
    }

    private void joined(){

    }

    private void initPresenter() {
        HoatDongRepository hoatDongRepository = new HoatDongRepository(HoatDongLocalDataSource.getInstance(),
                HoatDongRemoteDataSource.getInstance(this));
        UserHoatDongRepository userHoatDongRepository = new UserHoatDongRepository(UserHoatDongLoacalDataSource.getInstance(),
                UserHoatDongRemoteDataSource.getInstance(this));
        mPresenter = new HoatDongDetailPresenter(hoatDongRepository, userHoatDongRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);

        mPresenter.getHoatDong(AppConstants.BEARER + appPref.getApiToken(), idHoatDong);
        mPresenter.listUserNotJoin(AppConstants.BEARER + appPref.getApiToken(), idHoatDong);
    }

    private void getData() {
        appPref = new AppPref(this);
        idHoatDong = getIntent().getIntExtra(AppConstants.ID, 0);
    }

    private void initProgressbar(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(R.string.connect_to_server);
        progressDialog.setMessage(getResources().getString(R.string.messageConnectToServe));
        progressDialog.setCancelable(false);
    }

    @Override
    public void showProgressbar() {
        if (progressDialog == null) initProgressbar();
        if (!progressDialog.isShowing()) progressDialog.show();
    }

    @Override
    public void dimissProgresbar() {
        progressDialog.dismiss();
    }

    @Override
    public void setHoatDong(HoatDong hoatDong) {
        txt_name.setText(hoatDong.getName());
        txt_desc.setText(hoatDong.getDesc());

        SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
        txtComing.setText(fm.format(hoatDong.getFromDate()));
        txtFinish.setText(fm.format(hoatDong.getEndDate()));
    }

    @Override
    public void setListUser(List<User> listUser) {
        this.users.addAll(listUser);
        hoatDongDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void checkIn(int possition) {
        CheckInRequest checkInRequest = new CheckInRequest(users.get(possition).getId(), idHoatDong);
        mPresenter.checkIn(AppConstants.BEARER + appPref.getApiToken(), checkInRequest, possition);
    }

    @Override
    public void checkInSuccess(CheckInResponse checkInResponse, int possition) {
        Toast.makeText(this, users.get(possition).getUsername()
                + " " + getString(R.string.check_in_success), Toast.LENGTH_SHORT).show();
        users.remove(possition);
        hoatDongDetailAdapter.notifyDataSetChanged();
    }
}
