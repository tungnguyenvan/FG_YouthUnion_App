package com.dev.nguyenvantung.fg_app.ui.lcdoandetail;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.LCDoanDetailRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoandetail.LCDoanDetailResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;

public class LCDoanDetailPresenter implements LCDoanDetailConstract.Presenter{

    private static final String TAG = LCDoanDetailPresenter.class.getName();
    private LCDoanDetailConstract.View mView;
    private LCDoanDetailRepository mRepository;
    private SchedulerProvider mSchedulerProvider;

    public LCDoanDetailPresenter(LCDoanDetailRepository mRepository, SchedulerProvider mSchedulerProvider) {
        this.mRepository = mRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    private void handleSuccess(LCDoanDetailResponse lcDoanDetailResponse) {
//        mView.setListUser(lcDoanDetailResponse.getData());
    }

    private void handleError(Throwable e) {
        Log.e(TAG, e.getMessage());
    }

    private void handleGetLCDoanSuccess(LCDoanDetailResponse lcDoanDetailResponse){
        mView.dimissProgressbar();
        mView.setLCDoan(lcDoanDetailResponse.getLcDoan());
    }

    private void handleGetLCDoanError(Throwable e){
        mView.dimissProgressbar();
        Log.e(TAG, e.getMessage());
    }

    @Override
    public void getLCDoan(String token, int id) {
        mView.showProgressbar();
        mRepository.getLCDoan(token, id)
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
                        handleGetLCDoanError(e);
                    }
                });
    }

    @Override
    public void listUser(String token) {
        mRepository.listUser(token)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SingleObserver<LCDoanDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LCDoanDetailResponse lcDoanDetailResponse) {
//                        handleSuccess(lcDoanDetailResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
//                        handleError(e);
                    }
                });
    }

    @Override
    public void setView(LCDoanDetailConstract.View mView) {
        this.mView = mView;
    }
}
