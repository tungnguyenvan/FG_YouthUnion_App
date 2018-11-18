package com.dev.nguyenvantung.fg_app.ui.user;

import android.util.Log;

import com.dev.nguyenvantung.fg_app.data.repository.UserRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserDetailResponse;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;


public class UserPresenter implements UserConstact.Presenter {
    public static final String TAG = UserPresenter.class.getName();
    private UserConstact.View mView;
    private UserRepository mUserRepository;
    private SchedulerProvider mSchedulerProvider;

    public UserPresenter(UserRepository userRepository, SchedulerProvider schedulerProvider){
        mUserRepository = userRepository;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void setView(UserConstact.View view) {
        this.mView = view;
    }

    @Override
    public void userDetail(String token, int id) {
        mView.showProgressBar();
        mUserRepository.userDetail(token, id)
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(userResponse -> handleSuccess(userResponse),
                        error -> handleError(error));
    }

    private void handleSuccess(UserDetailResponse userDetailResponse) {
        mView.dismissProgressBar();
        mView.setUserDetail(userDetailResponse);
    }

    private void handleError(Throwable e) {
        mView.dismissProgressBar();
        Log.d(TAG, e.toString());
    }
}
