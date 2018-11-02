package com.dev.nguyenvantung.fg_app.ui.lcdoandetail;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.LCDoanDetailRepository;
import com.dev.nguyenvantung.fg_app.data.repository.UserRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoandetail.LCDoanDetailResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class LCDoanDetailPresenter implements LCDoanDetailConstract.Presenter{

    private static final String TAG = LCDoanDetailPresenter.class.getName();
    private LCDoanDetailConstract.View mView;
    private LCDoanDetailRepository mLCDoanDetailRepository;
    private UserRepository mUserRepository;
    private SchedulerProvider mSchedulerProvider;

    public LCDoanDetailPresenter(LCDoanDetailRepository mRepository,UserRepository mUserRepository, SchedulerProvider mSchedulerProvider) {
        this.mLCDoanDetailRepository = mRepository;
        this.mUserRepository = mUserRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    private void handleGetLCDoanSuccess(LCDoanDetailResponse lcDoanDetailResponse){
        mView.dimissProgressbar();
        mView.setLCDoan(lcDoanDetailResponse.getLcDoan());
    }

    @Override
    public void getLCDoan(String token, int id) {
        mView.showProgressbar();
        mLCDoanDetailRepository.getLCDoan(token, id)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SingleObserver<LCDoanDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LCDoanDetailResponse lcDoanDetailResponse) {
                        handleGetLCDoanSuccess(lcDoanDetailResponse);

                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }
                });
    }

    @Override
    public void listUserLCDoan(String token, int id) {
        mUserRepository.listUserLCDoan(token, id)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SingleObserver<UserResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(UserResponse userResponse) {
                        handleGetListUser(userResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }
                });
    }

    private void handleError(Throwable e) {
        Log.d(TAG, e.getMessage());
    }

    private void handleGetListUser(UserResponse userResponse) {
        mView.dimissProgressbar();
        mView.setListUser(userResponse.getData());
    }

    @Override
    public void setView(LCDoanDetailConstract.View mView) {
        this.mView = mView;
    }
}
