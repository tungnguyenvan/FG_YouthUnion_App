package com.dev.nguyenvantung.fg_app.ui.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.dev.nguyenvantung.fg_app.data.repository.UserRepository;
import com.dev.nguyenvantung.fg_app.data.source.local.UserLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.UserRemoteDataSource;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity implements UserConstact.View {
    public static final String TAG = "UserActivity";
    @BindView(R.id.button)
    public Button button;

    private UserConstact.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        UserRepository userRepository = new UserRepository(UserLocalDataSource.getInstance(),
                UserRemoteDataSource.getInstance(this));
        mPresenter = new UserPresenter(userRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.listUser(AppConstants.BEARER + AppPref.getInstance(UserActivity.this).getApiToken());
            }
        });
    }

    @Override
    public void setListUser(List<User> listUser) {
        Log.d(TAG, listUser.size() + "");
    }
}
