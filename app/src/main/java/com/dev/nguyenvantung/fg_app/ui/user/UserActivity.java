package com.dev.nguyenvantung.fg_app.ui.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.repository.UserRepository;
import com.dev.nguyenvantung.fg_app.data.source.local.UserLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.UserRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.user.UserDetailResponse;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.helper.DateHelper;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity implements UserConstact.View {
    public static final String TAG = UserActivity.class.getName();

    @BindView(R.id.txt_user_name)
    TextView txt_user_name;
    @BindView(R.id.txt_user_email)
    TextView txt_email;
    @BindView(R.id.txt_user_full_name)
    TextView txt_full_name;
    @BindView(R.id.txt_user_gender)
    TextView txt_gender;
    @BindView(R.id.txt_user_date)
    TextView txt_date;
    @BindView(R.id.txt_user_phone)
    TextView txt_phone;
    @BindView(R.id.txt_user_address)
    TextView txt_address;
    @BindView(R.id.txt_user_class)
    TextView txt_class;
    @BindView(R.id.user_progress)
    ProgressBar progressBar;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    private UserConstact.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        initView();

        UserRepository userRepository = new UserRepository(UserLocalDataSource.getInstance(),
                UserRemoteDataSource.getInstance(this));

        mPresenter = new UserPresenter(userRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);
        mPresenter.userDetail(AppConstants.BEARER + AppPref.getInstance(this).getApiToken(), AppConstants.USER.getId());

    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        toolbar.setTitle(R.string.info);
    }


    @Override
    public void setUserDetail(UserDetailResponse userDetail) {
        Log.d(TAG, userDetail.getData().getMiddleName() + "---" + AppConstants.USER.getUsername());
        txt_user_name.setText(AppConstants.USER.getUsername());
        txt_email.setText(AppConstants.USER.getEmail());
        String fullName = userDetail.getData().getFirstName()+ " " + userDetail.getData().getMiddleName() + " " + userDetail.getData().getLastName();
        txt_full_name.setText(fullName);
        if (userDetail.getData().getGender() == 1) {
            txt_gender.setText("Nam");
        }else txt_gender.setText("Nữ");
        DateHelper dateHelper = new DateHelper();
        String date = dateHelper.dateStringToRequest(userDetail.getData().getDateOfBirth());
        txt_date.setText(date);
        txt_address.setText(userDetail.getData().getAddress());
        txt_phone.setText(userDetail.getData().getPhone());
        txt_class.setText(userDetail.getData().getLop().getName());


    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
