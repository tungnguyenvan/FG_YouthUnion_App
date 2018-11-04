package com.dev.nguyenvantung.fg_app.ui.hoatdong.fragment.finished;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.HoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongsResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

public class HoatDongFinishedPresenter implements HoatDongFinishedConstract.Presenter {
    public static final String TAG = "HDEndedPresenter: ";
    private HoatDongFinishedConstract.View mView;
    private HoatDongRepository mHoatDongRepository;
    private SchedulerProvider mSchedulerProvider;

    public HoatDongFinishedPresenter(HoatDongRepository mHoatDongRepository, SchedulerProvider mSchedulerProvider) {
        this.mHoatDongRepository = mHoatDongRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    public void setView(HoatDongFinishedConstract.View view) {
        this.mView = view;
    }

    @Override
    public void listHoatDongFinished(String token) {
        mView.showProgressBar();
        mHoatDongRepository.listHoatDongFinished(token)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(hoatDongsResponse -> handleSuccess(hoatDongsResponse),
                        error -> handleError(error));
    }

   private void handleSuccess(HoatDongsResponse hoatDongsResponse) {
        mView.dismissProgressBar();
        mView.setListHoatDongFinished(hoatDongsResponse.getData());
   }

    private void handleError(Throwable e) {
        mView.dismissProgressBar();
        Log.d(TAG, e.toString());
    }
}
