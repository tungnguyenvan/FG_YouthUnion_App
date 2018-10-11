package com.dev.nguyenvantung.fg_app.ui.user;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.UserRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class UserPresenter implements UserConstact.Presenter {
    public static final String TAG = "UserPresenter";
    private UserConstact.View mView;
    private UserRepository mUserRepository;
    private SchedulerProvider mSchedulerProvider;

    public UserPresenter(UserRepository userRepository, SchedulerProvider schedulerProvider){
        mUserRepository = userRepository;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void listUser(String token) {
        mUserRepository.listUser(token)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(new SingleObserver<UserResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(UserResponse userResponse) {
                        handleSuccess(userResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }
                });
    }

    @Override
    public void setView(UserConstact.View view) {
        this.mView = view;
    }

    private void handleSuccess(UserResponse userResponse) {
        mView.setListUser(userResponse.getData());
    }

    private void handleError(Throwable e) {
        Log.d(TAG, e.toString());
    }
}
