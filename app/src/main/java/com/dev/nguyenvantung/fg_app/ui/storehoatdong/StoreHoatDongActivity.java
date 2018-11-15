package com.dev.nguyenvantung.fg_app.ui.storehoatdong;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDongRequest;
import com.dev.nguyenvantung.fg_app.data.model.hoatdongtype.HoatDongType;
import com.dev.nguyenvantung.fg_app.data.repository.HoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.repository.HoatDongTypeRepository;
import com.dev.nguyenvantung.fg_app.data.source.local.HoatDongLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.local.HoatDongTypeLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.HoatDongRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.HoatDongTypeRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongsResponse;
import com.dev.nguyenvantung.fg_app.ui.storehoatdong.adapter.HoatDongTypeAdapter;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.Validation;
import com.dev.nguyenvantung.fg_app.utils.helper.CalendaHelper;
import com.dev.nguyenvantung.fg_app.utils.helper.DateHelper;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreHoatDongActivity extends AppCompatActivity
        implements StoreHoatDongConstact.View, View.OnClickListener {
    private static final String TAG = StoreHoatDongActivity.class.getName();
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.store_hoatdong_collapsing_start)
    public CollapsibleCalendar calendar_start;
    @BindView(R.id.store_hoatdong_ed_name)
    public EditText ed_name;
    @BindView(R.id.store_hoatdong_txt_start)
    public TextView txt_start;
    @BindView(R.id.store_hoatdong_txt_end)
    public TextView txt_end;
    @BindView(R.id.store_hoatdong_collapsing_end)
    public CollapsibleCalendar calendar_end;
    @BindView(R.id.store_hoatdong_ed_desc)
    public EditText ed_desc;
    @BindView(R.id.store_hoatdong_btn_create)
    public Button btn_create;
    @BindView(R.id.store_hoatdong_sp_type)
    public Spinner store_hoatdong_sp_type;

    private ProgressDialog mProgressDialog;
    private StoreHoatDongConstact.Presenter mPresenter;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date date = new Date();

    private List<HoatDongType> hoatDongTypes;
    private HoatDongTypeAdapter hoatDongTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_hoat_dong);
        ButterKnife.bind(this);

        HoatDongTypeRepository hoatDongTypeRepository = new HoatDongTypeRepository(HoatDongTypeLocalDataSource.getInstance(),
                HoatDongTypeRemoteDataSource.getTnstance(this));
        HoatDongRepository storeHoatDongRepository = new HoatDongRepository(HoatDongLocalDataSource.getInstance(),
                HoatDongRemoteDataSource.getInstance(this));
        mPresenter = new StoreHoatDongPresenter(hoatDongTypeRepository, storeHoatDongRepository,
                SchedulerProvider.getInstance());
        mPresenter.setView(this);

        initView();
    }

    private void initView() {
        toolbar.setTitle(R.string.title_create_hoatdong);
        toolbar.setNavigationIcon(R.drawable.ic_arraw_white);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        txt_start.setText(simpleDateFormat.format(date));
        txt_end.setText(simpleDateFormat.format(date));

        btn_create.setOnClickListener(this);

        hoatDongTypes = new ArrayList<>();
        hoatDongTypeAdapter = new HoatDongTypeAdapter(hoatDongTypes, this);
        store_hoatdong_sp_type.setAdapter(hoatDongTypeAdapter);
        mPresenter.listHoatDongType(AppConstants.BEARER + AppPref.getInstance(this).getApiToken());


        calendar_start.setCalendarListener(new CalendaHelper(calendar_start, txt_start));
        calendar_end.setCalendarListener(new CalendaHelper(calendar_end, txt_end));
    }

    private void initProgressDialog(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle(getString(R.string.connect_to_server));
        mProgressDialog.setMessage(getString(R.string.messageConnectToServe));
    }

    private void createHoatDong(){
        HoatDongRequest hoatDongRequest = new HoatDongRequest(
                ed_name.getText().toString(),
                ed_desc.getText().toString(),
                txt_start.getText().toString(),
                txt_end.getText().toString(),
                String.valueOf(store_hoatdong_sp_type.getSelectedItemPosition() + 1));

        Validation validation = new Validation();
        if (validation.checkStoreDataHoatDong(this, hoatDongRequest, ed_name, txt_start, txt_end,
                store_hoatdong_sp_type, ed_desc))
            mPresenter.storeHoatDong(AppConstants.BEARER + AppPref.getInstance(this).getApiToken(),
                    hoatDongRequest);

    }

    @Override
    public void showProgressbar() {
        if (mProgressDialog == null) initProgressDialog();
        mProgressDialog.show();
    }

    @Override
    public void dimissProgressbar() {
        mProgressDialog.dismiss();
    }

    @Override
    public void setListHoatDongType(List<HoatDongType> hoatDongTypes) {
        this.hoatDongTypes.addAll(hoatDongTypes);
        hoatDongTypeAdapter.notifyDataSetChanged();
    }

    @Override
    public void createHoatDongSuccess(HoatDongsResponse hoatDong) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.store_hoatdong_success));
        builder.setMessage(ed_name.getText().toString() + " " + getString(R.string.created_success));
        builder.setPositiveButton(getString(R.string.ok), ((dialog, which) -> onBackPressed()));
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.store_hoatdong_btn_create:
                createHoatDong();
                break;
        }
    }

}
