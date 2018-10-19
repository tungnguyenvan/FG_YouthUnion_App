package com.dev.nguyenvantung.fg_app.ui.lcdoandetail;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.LCDoanDetailRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.lcdoandetail.LCDoanDetailResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class LCDoanDetailPresenter implements LCDoanDetailConstract.Presenter{

    private static final String TAG = "LCDoanDetail";
    private LCDoanDetailConstract.View mView;
    private LCDoanDetailRepository mRepository;
    private SchedulerProvider mSchedulerProvider;

    public LCDoanDetailPresenter(LCDoanDetailRepository mRepository, SchedulerProvider mSchedulerProvider) {
        this.mRepository = mRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void ListUser(String token) {
        mRepository.listUser(token)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SingleObserver<LCDoanDetailResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LCDoanDetailResponse lcDoanDetailResponse) {
                        handleSuccess(lcDoanDetailResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }
                });
    }

    private void handleError(Throwable e) {
        Log.e(TAG, e.toString());
    }

    private void handleSuccess(LCDoanDetailResponse lcDoanDetailResponse) {
        mView.setListUser(lcDoanDetailResponse.getData());
    }

    @Override
    public void setView(LCDoanDetailConstract.View mView) {
        this.mView = mView;
    }
}
