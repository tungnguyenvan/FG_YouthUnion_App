package com.dev.nguyenvantung.fg_app.ui.splash;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;
import com.dev.nguyenvantung.fg_app.data.repository.LoginRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.LoginRemoteDataSource;
import com.dev.nguyenvantung.fg_app.ui.login.LoginActivity;
import com.dev.nguyenvantung.fg_app.ui.main.MainActivity;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.navigator.Navigator;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements SplashContract.View{
    private static final String TAG = SplashActivity.class.getName();
    private static final int SPLASH_DISPLAY_LENGHT = 1000;

    @BindView(R.id.splah_version)
    public TextView txtVersion;

    @BindView(R.id.splash_logo_layout)
    public LinearLayout mLinearLayout;

    private AppPref mAppPref;
    private SplashContract.Presenter mPresenter;
    private Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        mAppPref = AppPref.getInstance(this);
        mNavigator = new Navigator(this);

        //set version
        try {
            String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            txtVersion.setText(getString(R.string.version) + versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        initPresenter();
        checkLogin();
    }

    private void initPresenter() {
        LoginRepository loginRepository = new LoginRepository(LoginRemoteDataSource.getInstance(this));
        mPresenter = new SplashPresenter(loginRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);
    }

    private void checkLogin() {
        if (mAppPref.getRemember() && !mAppPref.getEmail().isEmpty() && !mAppPref.getPassword().isEmpty()){
            mPresenter.login(new LoginRequesst(mAppPref.getEmail(), mAppPref.getPassword(), mAppPref.getRemember()));
        }else {
            new Handler().postDelayed(() -> nextToLoginActivity(), SPLASH_DISPLAY_LENGHT);
        }
    }

    private void nextToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        mNavigator.startActivity(intent, mLinearLayout, getString(R.string.share_view));
    }

    private void nextToMain(){
        mNavigator.startActivity(MainActivity.class);
    }

    @Override
    public void loginSuccess() {
        nextToMain();
    }

    @Override
    public void loginError() {
        nextToLoginActivity();
    }
}
