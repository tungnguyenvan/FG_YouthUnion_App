package com.dev.nguyenvantung.fg_app.ui.main;


import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.repository.AuthRepository;
import com.dev.nguyenvantung.fg_app.data.source.remote.AuthRemoteDataSource;
import com.dev.nguyenvantung.fg_app.ui.hoatdong.HoatDongFragment;
import com.dev.nguyenvantung.fg_app.ui.lcdoan.LCDoanFragment;
import com.dev.nguyenvantung.fg_app.ui.login.LoginActivity;
import com.dev.nguyenvantung.fg_app.ui.user.UserActivity;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.navigator.Navigator;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        MainContract.View {
    private static final String TAG = MainActivity.class.getName();

    @BindView(R.id.main_drawlayout)
    public DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;

    @BindView(R.id.main_navigation_view)
    public NavigationView mainNavigationView;

    @BindView(R.id.main_img_menu)
    public ImageView imgMenu;

    @BindView(R.id.main_framelayout)
    public FrameLayout frameLayout;

    private FragmentManager mFragmentManager;
    private Fragment mActive;
    private Fragment hoatDongFragment = HoatDongFragment.getInstance(),
            lcdoanFragment = LCDoanFragment.getInstance();
    private MainContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;
    private Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        AuthRepository authRepository = new AuthRepository(AuthRemoteDataSource.getInstance(this));
        mPresenter = new MainPresenter(authRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);

        mNavigator = new Navigator(this);

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(frameLayout.getId(), hoatDongFragment)
                .add(frameLayout.getId(), lcdoanFragment).hide(lcdoanFragment).commit();
        mActive = HoatDongFragment.getInstance();

        initDrawerLayout();

        initNavHeaderLayout();

        imgMenu.setOnClickListener(view -> drawerLayout.openDrawer(Gravity.START));
    }

    private void initDrawerLayout(){
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        mainNavigationView.setNavigationItemSelectedListener(this);
    }

    private void initNavHeaderLayout() {
        View header_view = mainNavigationView.inflateHeaderView(R.layout.navigation_header);
        TextView txt_nav_name = header_view.findViewById(R.id.txt_nav_name);
        txt_nav_name.setText(AppConstants.USER.getUsername());
        LinearLayout navLayout = header_view.findViewById(R.id.nav_layout);
        navLayout.setOnClickListener(view -> toUserActivity());
    }

    private void nextFragment(Fragment fragment){
        mFragmentManager.beginTransaction().hide(mActive).show(fragment).commit();
        mActive = fragment;
    }

    private void logOut(){
        mPresenter.logOut(AppConstants.BEARER + AppPref.getInstance(this).getApiToken());
    }

    private void initProgress(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(getString(R.string.log_out));
        mProgressDialog.setMessage(getString(R.string.messageConnectToServe));
    }

    private void toUserActivity(){
        mNavigator.startActivity(UserActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.mnu_hoatdong:
                nextFragment(hoatDongFragment);
                break;
            case R.id.mnu_lcdoan:
                nextFragment(lcdoanFragment);
                break;
            case R.id.mnu_logout:
                logOut();
                break;
        }
        drawerLayout.closeDrawer(Gravity.START);
        return false;
    }

    @Override
    public void showProgress() {
        if (mProgressDialog == null) initProgress();
        mProgressDialog.show();
    }

    @Override
    public void dimissProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public void logOutSuccess() {
        AppPref appPref = AppPref.getInstance(this);

        appPref.putApiToken(null);
        appPref.putPassword(null);
        appPref.putRememberMe(false);

        mNavigator.startActivity(LoginActivity.class);
        finish();
    }

    @Override
    public void logOutFail() {
        Toast.makeText(this, "Đăng xuất thất bại, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
    }
}
