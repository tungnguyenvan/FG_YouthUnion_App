package com.dev.nguyenvantung.fg_app.ui.main.fragment.hoatdong;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.HoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class HoatDongPresenter implements HoatDongConstract.Presenter{
    public static final String TAG = "HoatDongPresenter: ";
    private HoatDongConstract.View mView;
    private HoatDongRepository mHoatDongRepository;
    private SchedulerProvider mSchedulerProvider;

    public HoatDongPresenter(HoatDongRepository mHoatDongRepository, SchedulerProvider mSchedulerProvider) {
        this.mHoatDongRepository = mHoatDongRepository;
        this.mSchedulerProvider = mSchedulerProvider;
    }

    @Override
    public void listHoatDong(String token) {
        mHoatDongRepository.listHoatDong(token)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SingleObserver<HoatDongResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(HoatDongResponse hoatDongResponse) {
                        handleSuccess(hoatDongResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }
                });
    }

    public void setView(HoatDongConstract.View view) {
        this.mView = view;
    }

   private void handleSuccess(HoatDongResponse hoatDongResponse) {
        mView.setListHoatDong(hoatDongResponse.getData());
   }

    private void handleError(Throwable e) {
        Log.d(TAG, e.toString());
    }
}
