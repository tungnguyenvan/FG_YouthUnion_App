package com.dev.nguyenvantung.fg_app.ui.lcdoandetail;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.LCDoanRepository;
import com.dev.nguyenvantung.fg_app.data.repository.UserRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoan.LCDoanResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoandetail.LCDoanDetailResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UsersResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class LCDoanDetailPresenter implements LCDoanDetailConstract.Presenter{

    private static final String TAG = LCDoanDetailPresenter.class.getName();
    private LCDoanDetailConstract.View mView;
    private LCDoanRepository mLCDoanRepository;
    private UserRepository mUserRepository;
    private SchedulerProvider mSchedulerProvider;

    public LCDoanDetailPresenter(UserRepository mUserRepository, LCDoanRepository mLCDoanRepository, SchedulerProvider mSchedulerProvider) {
        this.mUserRepository = mUserRepository;
        this.mLCDoanRepository = mLCDoanRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }


    @Override
    public void getLCDoan(String token, int id) {
        mView.showProgressbar();
        mLCDoanRepository.LCDoan(token, id)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SingleObserver<LCDoanDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LCDoanDetailResponse lcDoanDetailResponse) {
                        handleGetLCDoan(lcDoanDetailResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void handleGetLCDoan(LCDoanDetailResponse lcDoanDetailResponse) {
        mView.dimissProgressbar();
        mView.setLCDoan(lcDoanDetailResponse.getLcDoan());
    }

    @Override
    public void listUserLCDoan(String token, int id) {
        mUserRepository.listUserLCDoan(token, id)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(usersResponse -> handleSuccess(usersResponse),
                        error -> handleError(error));

    }

    private void handleSuccess(UsersResponse usersResponse) {
        mView.dimissProgressbar();
        mView.setListUser(usersResponse.getData());
    }

    private void handleError(Throwable e) {
        Log.d(TAG, e.getMessage());
    }


    @Override
    public void setView(LCDoanDetailConstract.View mView) {
        this.mView = mView;
    }
}
