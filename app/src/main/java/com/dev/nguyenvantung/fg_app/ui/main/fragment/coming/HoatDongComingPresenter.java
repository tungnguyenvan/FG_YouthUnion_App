package com.dev.nguyenvantung.fg_app.ui.main.fragment.coming;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.HoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongResponse;
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
    public void listHoatDongComing(String token) {
        mRepository.listHoatDongComming(token)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SingleObserver<HoatDongResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(HoatDongResponse hoatDongResponse) {
                        Log.d(TAG, "success");
                        handleHoatDongComingSuccess(hoatDongResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleHoatDongComingError(e);
                    }
                });
    }

    private void handleHoatDongComingSuccess(HoatDongResponse hoatDongResponse) {
        mView.setListHoatDongComing(hoatDongResponse.getData());
    }

    private void handleHoatDongComingError(Throwable e) {
        Log.e(TAG, e.toString());
    }
    @Override
    public void setView(HoatDongComingConstract.View view) {
        this.mView = view;
    }
}
