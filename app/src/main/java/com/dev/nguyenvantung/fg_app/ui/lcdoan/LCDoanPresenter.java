package com.dev.nguyenvantung.fg_app.ui.lcdoan;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.LCDoanRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoan.LCDoanResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class LCDoanPresenter implements LCDoanConstact.Presenter {
    private static final String TAG = "LCDoanPresenter";
    private LCDoanConstact.View mView;
    private LCDoanRepository mLcDoanRepository;
    private SchedulerProvider mSchedulerProvider;

    public LCDoanPresenter(LCDoanRepository mLcDoanRepository, SchedulerProvider mSchedulerProvider){
        this.mLcDoanRepository = mLcDoanRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }



    @Override
    public void listLCDoan(String token) {
        mLcDoanRepository.listLCDoan(token)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SingleObserver<LCDoanResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LCDoanResponse lcDoanResponse) {
                        handleSuccess(lcDoanResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }
                });
    }

    @Override
    public void setView(LCDoanConstact.View mView) {
        this.mView = mView;
    }

    private void handleSuccess(LCDoanResponse mLcDoanResponse){
        mView.setListLCDoan(mLcDoanResponse.getData());
    }

    private void handleError(Throwable e) {
        Log.e(TAG, e.toString());
    }
}
