package com.dev.nguyenvantung.fg_app.ui.storehoatdong;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDongRequest;
import com.dev.nguyenvantung.fg_app.data.model.hoatdongtype.HoatDongType;
import com.dev.nguyenvantung.fg_app.data.repository.HoatDongTypeRepository;
import com.dev.nguyenvantung.fg_app.data.repository.StoreHoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.source.local.HoatDongTypeLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.local.StoreHoatDongLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.HoatDongTypeRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.StoreHoatDongRemoteDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.hoatdong.HoatDongResponse;
import com.dev.nguyenvantung.fg_app.data.source.remote.response.storehoatdong.StoreHoatDongResponse;
import com.dev.nguyenvantung.fg_app.ui.main.fragment.adapter.HoatDongAdapter;
import com.dev.nguyenvantung.fg_app.ui.storehoatdong.adapter.HoatDongTypeAdapter;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.helper.CalendaHelper;
import com.dev.nguyenvantung.fg_app.utils.helper.DateHelper;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;
import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.text.ParseException;
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
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
        StoreHoatDongRepository storeHoatDongRepository = new StoreHoatDongRepository(StoreHoatDongLocalDataSource.getInstance(),
                StoreHoatDongRemoteDataSource.getInstance(this));
        mPresenter = new StoreHoatDongPresenter(hoatDongTypeRepository, storeHoatDongRepository,
                SchedulerProvider.getInstance());
        mPresenter.setView(this);

        initView();
    }

    private void initView() {
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
        DateHelper dateHelper = new DateHelper();
        HoatDongRequest hoatDongRequest = new HoatDongRequest(
                ed_name.getText().toString(),
                ed_desc.getText().toString(),
                dateHelper.dateStringToRequest(txt_start.getText().toString()),
                dateHelper.dateStringToRequest(txt_end.getText().toString()),
                store_hoatdong_sp_type.getSelectedItemPosition() + 1
        );
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
    public void createHoatDongSuccess(HoatDongResponse hoatDong) {
        Toast.makeText(this, ed_name.getText().toString() + " Tạo thành công!", Toast.LENGTH_SHORT).show();
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
