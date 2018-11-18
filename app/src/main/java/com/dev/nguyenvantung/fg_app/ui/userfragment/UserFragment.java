package com.dev.nguyenvantung.fg_app.ui.userfragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.dev.nguyenvantung.fg_app.data.model.userhoatdong.UserHoatDong;
import com.dev.nguyenvantung.fg_app.data.repository.HoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.repository.UserHoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.repository.UserRepository;
import com.dev.nguyenvantung.fg_app.data.source.local.HoatDongLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.local.UserHoatDongLoacalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.local.UserLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.HoatDongRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.UserHoatDongRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.UserRemoteDataSource;
import com.dev.nguyenvantung.fg_app.ui.userfragment.adapter.UserFragmentHoatDongAdapter;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserFragment extends BottomSheetDialogFragment implements UserFragmentContract.View {
    private static final String TAG = UserFragment.class.getName();

    @BindView(R.id.userfragment_pb)
    public ProgressBar progressBar;

    @BindView(R.id.userfragment_img_avatar)
    public ImageView imgAvatar;

    @BindView(R.id.userfragment_txt_name)
    public TextView txtName;

    @BindView(R.id.userfragment_txt_email)
    public TextView txtEmail;

    @BindView(R.id.userfragment_img_close)
    public View vClose;

    @BindView(R.id.userfragment_rv)
    public RecyclerView hoatDongRv;

    @BindView(R.id.userfragment_txt_notjoin)
    public TextView txtNotJoin;

    private static UserFragment instance;
    private Context context;
    private int mId;
    private UserFragmentHoatDongAdapter mAdapter;
    private List<UserHoatDong> mUserHoatDongs;
    private UserFragmentContract.Presenter mPresenter;

    public void addData(int mId){
        this.mId = mId;
    }

    static BottomSheetDialogFragment newInstance(){
        return new BottomSheetDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_user, container);
        ButterKnife.bind(this, view);
        initView();
        initPresenter();
        initData();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.setBackgroundColor(Color.TRANSPARENT);
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(){
        mUserHoatDongs = new ArrayList<>();
        mAdapter = new UserFragmentHoatDongAdapter(mUserHoatDongs, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        hoatDongRv.setHasFixedSize(true);
        hoatDongRv.setLayoutManager(linearLayoutManager);
        hoatDongRv.setAdapter(mAdapter);
    }

    private void initPresenter(){
        UserRepository userRepository = new UserRepository(UserLocalDataSource.getInstance(),
                UserRemoteDataSource.getInstance(getContext()));
        UserHoatDongRepository userHoatDongRepository = new UserHoatDongRepository(UserHoatDongLoacalDataSource.getInstance(),
                UserHoatDongRemoteDataSource.getInstance(getContext()));
        mPresenter = new UserFragmentPresenter(userRepository, userHoatDongRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);
    }

    private void initData(){
        mPresenter.getUser(AppConstants.BEARER + AppPref.getInstance(getContext()).getApiToken(),
                mId);
        mPresenter.getHoatDongJoined(AppConstants.BEARER + AppPref.getInstance(getContext())
                .getApiToken(),mId);
    }

    private void dimissNotJoin(){
        txtNotJoin.setVisibility(View.GONE);
        hoatDongRv.setVisibility(View.VISIBLE);
    }

    private void showNotJoin(){
        txtNotJoin.setVisibility(View.VISIBLE);
        hoatDongRv.setVisibility(View.GONE);
    }

    @Override
    public void setDataUser(User mUser) {
        txtName.setText(mUser.getUsername());
        txtEmail.setText(mUser.getEmail());
    }

    @Override
    public void setDataHoatDong(List<UserHoatDong> mUserHoatDongs){
        this.mUserHoatDongs.addAll(mUserHoatDongs);
        if (this.mUserHoatDongs.size() <= 0) showNotJoin();
        else dimissNotJoin();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        vClose.setVisibility(View.GONE);
    }

    @Override
    public void dimissProgress() {
        progressBar.setVisibility(View.GONE);
        vClose.setVisibility(View.VISIBLE);
    }
}
