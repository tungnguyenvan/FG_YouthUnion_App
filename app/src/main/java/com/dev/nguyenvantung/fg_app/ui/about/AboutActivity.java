package com.dev.nguyenvantung.fg_app.ui.about;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.BuildConfig;
import com.dev.nguyenvantung.fg_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {
    @BindView(R.id.about_txt_version)
    public TextView txtVersion;

    @BindView(R.id.about_txt_version_phone)
    public TextView txtVersionPhone;

    @BindView(R.id.toolbar)
    public Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        intiView();
    }

    private void intiView() {
        txtVersion.setText(BuildConfig.VERSION_NAME);
        txtVersionPhone.setText("Android " + Build.VERSION.RELEASE +" - "+ Build.BRAND + " " + Build.MODEL);
        toolBar.setTitle(R.string.about);
        toolBar.setNavigationIcon(R.drawable.ic_arraw_white);
        toolBar.setNavigationOnClickListener(view -> onBackPressed());
    }
}
