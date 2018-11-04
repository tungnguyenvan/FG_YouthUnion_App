package com.dev.nguyenvantung.fg_app.ui.hoatdong.fragment.coming;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.HoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongsResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class HoatDongComingPresenter implements HoatDongComingConstract.Presenter{
    private static final String TAG = HoatDongComingPresenter.class.getName();
    private HoatDongComingConstract.View mView;
    private HoatDongRepository mRepository;
    private SchedulerProvider mSchedulerProvider;

    public HoatDongComingPresenter(HoatDongRepository mRepository, SchedulerProvider mSchedulerProvider) {
        this.mRepository = mRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void setView(HoatDongComingConstract.View view) {
        this.mView = view;
    }

    @Override
    public void listHoatDongComing(String token) {
        mView.showProgressBar();
        mRepository.listHoatDongComming(token)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(hoatDongsResponse -> handleSuccess(hoatDongsResponse),
                        error -> handleError(error));
    }

    private void handleSuccess(HoatDongsResponse hoatDongsResponse) {
        mView.dismissProgressBar();
        mView.setListHoatDongComing(hoatDongsResponse.getData());
    }

    private void handleError(Throwable e) {
        Log.e(TAG, e.toString());
    }
}
