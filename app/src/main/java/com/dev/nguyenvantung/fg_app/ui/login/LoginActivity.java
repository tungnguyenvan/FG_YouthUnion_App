package com.dev.nguyenvantung.fg_app.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.login.LoginRequesst;
import com.dev.nguyenvantung.fg_app.data.repository.LoginRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.LoginRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.login.LoginResponse;
import com.dev.nguyenvantung.fg_app.ui.main.MainActivity;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.Validation;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginConstact.View, View.OnClickListener {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.login_btn_sigin)
    public Button btnSigin;
    @BindView(R.id.login_txt_email)
    public EditText edEmail;
    @BindView(R.id.login_txt_password)
    public EditText edPassword;
    @BindView(R.id.login_remenber_me)
    public CheckBox loginRemenberMe;

    private ProgressDialog mProgressDialog;
    private Validation mValidation;
    private LoginConstact.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //initProgress
        initProgressbar();

        //set Presenter
        LoginRepository loginRepository = new LoginRepository(LoginRemoteDataSource.getInstance(this));
        mPresenter  = new LoginPresenter(loginRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);

        //setAction
        btnSigin.setOnClickListener(this);
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
            case R.id.login_btn_sigin:
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
        AppPref.getInstance(this).putApiToken(mLoginResponse.getAccess_token());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginFails() {
        Toast.makeText(this, "Đăng nhập thất bại, Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
    }
}
