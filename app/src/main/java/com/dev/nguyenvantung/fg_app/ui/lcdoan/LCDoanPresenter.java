package com.dev.nguyenvantung.fg_app.ui.lcdoan;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.KhoaRepositoty;
import com.dev.nguyenvantung.fg_app.data.repository.LCDoanRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.khoa.KhoaResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoan.LCDoanResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class LCDoanPresenter implements LCDoanConstact.Presenter {
    private static final String TAG = LCDoanPresenter.class.getName();
    private LCDoanConstact.View mView;
    private LCDoanRepository mLCDoanRepository;
    private KhoaRepositoty mKhoaRepositoty;
    private SchedulerProvider mSchedulerProvider;

    public LCDoanPresenter(LCDoanRepository mLcDoanRepository, KhoaRepositoty khoaRepositoty, SchedulerProvider mSchedulerProvider){
        this.mLCDoanRepository = mLcDoanRepository;
        this.mKhoaRepositoty = khoaRepositoty;
        this.mSchedulerProvider = mSchedulerProvider;
    }


    @Override
    public void ListLCDoanById(String token, int id) {
        mLCDoanRepository.listLCDById(token, id)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SingleObserver<LCDoanResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LCDoanResponse lcDoanResponse) {
                        handleSuccessLCDoan(lcDoanResponse);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void listKhoa(String token) {
        mKhoaRepositoty.listKhoa(token)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SingleObserver<KhoaResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(KhoaResponse khoaResponse) {
                        handleSuccessKhoa(khoaResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleErrorKhoa(e);
                    }
                });
    }

    private void handleSuccessLCDoanById(KhoaResponse khoaResponse) {
    }

    private void handleSuccessKhoa(KhoaResponse khoaResponse) {
        mView.setListKhoa(khoaResponse.getListKhoa());

    }

    private void handleErrorKhoa(Throwable e) {
        Log.e(TAG, e.toString());
    }

    @Override
    public void listLCDoan(String token) {
        mLCDoanRepository.listLCDoan(token)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SingleObserver<LCDoanResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LCDoanResponse lcDoanResponse) {
                        handleSuccessLCDoan(lcDoanResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleErrorLCDoan(e);
                    }
                });
    }

    private void handleSuccessLCDoan(LCDoanResponse mLcDoanResponse){
        mView.setListLCDoan(mLcDoanResponse.getData());
    }

    private void handleErrorLCDoan(Throwable e) {
        Log.e(TAG, e.toString());
    }

    @Override
    public void setView(LCDoanConstact.View mView) {
        this.mView = mView;
    }
}
