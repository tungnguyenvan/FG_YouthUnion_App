package com.dev.nguyenvantung.fg_app.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;
import com.dev.nguyenvantung.fg_app.data.repository.AuthRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.AuthRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.login.LoginResponse;
import com.dev.nguyenvantung.fg_app.ui.main.MainActivity;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.Validation;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginConstact.View, View.OnClickListener {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.login_btn_signin)
    public Button btnSigin;
    @BindView(R.id.login_edt_email)
    public EditText edEmail;
    @BindView(R.id.login_edt_password)
    public EditText edPassword;
    @BindView(R.id.login_cb_remember)
    public CheckBox loginRemenberMe;

    private ProgressDialog mProgressDialog;
    private Validation mValidation;
    private LoginConstact.Presenter mPresenter;
    private AnimationDrawable mAnimationDrawable;
    private AppPref mAppPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mAppPref = AppPref.getInstance(this);

        //set Presenter
        AuthRepository authRepository = new AuthRepository(AuthRemoteDataSource.getInstance(this));
        mPresenter  = new LoginPresenter(authRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);

        //initProgress
        initProgressbar();
        //setAction
        btnSigin.setOnClickListener(this);
        //init view
        initView();
        //check login
        checkLogin();
        //run Animation
//        runAnim();
    }

    private void runAnim() {
        mAnimationDrawable = (AnimationDrawable) btnSigin.getBackground();
        mAnimationDrawable.setEnterFadeDuration(5000);
        mAnimationDrawable.setExitFadeDuration(5000);
        mAnimationDrawable.start();
    }

    private void checkLogin() {
        if (mAppPref.getRemember()){
            login();
        }
    }

    private void initView() {
        edEmail.setText(mAppPref.getEmail());
        edPassword.setText(mAppPref.getPassword());
        loginRemenberMe.setChecked(mAppPref.getRemember());
    }

    private void initProgressbar(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(getResources().getString(R.string.login));
        mProgressDialog.setMessage(getResources().getString(R.string.messageConnectToServe));
        mProgressDialog.setCancelable(false);
    }

    private void login(){
        LoginRequesst loginRequesst = new LoginRequesst(edEmail.getText().toString(),
                edPassword.getText().toString(), loginRemenberMe.isChecked());
        mPresenter.login(loginRequesst);
    }

    private boolean checkValidation(){
        mValidation = new Validation();
        if (mValidation.checkEmail(edEmail.getText().toString())){
            edEmail.setError(getResources().getString(R.string.errorEmail));
            return false;
        }

        if (mValidation.checkPassword(edPassword.getText().toString())){
            edPassword.setError(getResources().getString(R.string.errorPassword));
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn_signin:
                if (checkValidation()) login();
                break;
        }
    }

    @Override
    public void showProgressbarDialog() {
        mProgressDialog.show();
    }

    @Override
    public void dimissProgressbarDialog() {
        mProgressDialog.dismiss();
    }

    @Override
    public void loginSuccess(LoginResponse mLoginResponse) {
        AppPref appPref = AppPref.getInstance(this);
        appPref.putApiToken(mLoginResponse.getAccess_token());
        appPref.putEmail(edEmail.getText().toString());
        appPref.putPassword(edPassword.getText().toString());
        appPref.putRememberMe(loginRemenberMe.isChecked());
        AppConstants.USER = mLoginResponse.getUser();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFails() {
        Toast.makeText(this, getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
    }
}
