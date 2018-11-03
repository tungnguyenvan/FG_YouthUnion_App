package com.dev.nguyenvantung.fg_app.ui.main.fragment.end;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.HoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongsResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class HoatDongFinishedPresenter implements HoatDongFinishedConstract.Presenter {
    public static final String TAG = "HDEndedPresenter: ";
    private HoatDongFinishedConstract.View mView;
    private HoatDongRepository mHoatDongRepository;
    private SchedulerProvider mSchedulerProvider;

    public HoatDongFinishedPresenter(HoatDongRepository mHoatDongRepository, SchedulerProvider mSchedulerProvider) {
        this.mHoatDongRepository = mHoatDongRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void listHoatDongFinished(String token) {
        mView.showProgressBar();
        mHoatDongRepository.listHoatDongFinished(token)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SingleObserver<HoatDongsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(HoatDongsResponse hoatDongsResponse) {
                        handleSuccess(hoatDongsResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }
                });
    }

    public void setView(HoatDongFinishedConstract.View view) {
        this.mView = view;
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
