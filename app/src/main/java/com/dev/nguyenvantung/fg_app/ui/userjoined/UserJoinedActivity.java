package com.dev.nguyenvantung.fg_app.ui.userjoined;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.dev.nguyenvantung.fg_app.data.repository.UserHoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.source.local.UserHoatDongLoacalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.UserHoatDongRemoteDataSource;
import com.dev.nguyenvantung.fg_app.ui.userjoined.adapter.UserJoinedAdapter;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserJoinedActivity extends AppCompatActivity implements UserJoinedContract.View{
    private static final String TAG = UserJoinedActivity.class.getName();

    @BindView(R.id.joined_recyclerview)
    public RecyclerView joinedRv;

    @BindView(R.id.joined_progress)
    public ProgressBar joinedPb;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    private int id;
    private UserJoinedContract.Presenter mPresenter;
    private UserJoinedAdapter mAdapter;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_joined);
        ButterKnife.bind(this);

        id = getIntent().getIntExtra(AppConstants.ID, 0);

        iniView();
        initPresenter();
        getData();
    }

    private void iniView() {
        toolbar.setTitle(getResources().getString(R.string.joined));
        toolbar.setNavigationIcon(R.drawable.ic_arraw_white);
        toolbar.setNavigationOnClickListener((view) -> onBackPressed());

        //init recyclerview
        users = new ArrayList<>();
        mAdapter = new UserJoinedAdapter(users);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        joinedRv.setHasFixedSize(true);
        joinedRv.setLayoutManager(linearLayoutManager);
        joinedRv.setAdapter(mAdapter);
    }

    private void initPresenter() {
        UserHoatDongRepository userHoatDongRepository = new UserHoatDongRepository(UserHoatDongLoacalDataSource.getInstance(),
                UserHoatDongRemoteDataSource.getInstance(this));
        mPresenter = new UserJoinedPresenter(userHoatDongRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);
    }

    private void getData() {
        if(id != 0) mPresenter.listUserJoined(AppConstants.BEARER + AppPref.getInstance(this).getApiToken(), id);
        else handleError(getString(R.string.get_id_error));
    }

    private void handleError(String error){
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        joinedPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void dimissProgress() {
        joinedPb.setVisibility(View.GONE);
    }

    @Override
    public void setListUser(List<User> users) {
        this.users.addAll(users);
        mAdapter.notifyDataSetChanged();
    }
}
