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
        mPresenter.listHoatDongType("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjYyOWQ5ZTc2N2NkMWMyYzQ0ZDA1MzBiNzE2YjE2MzNiZjFiODUwMTFkNTdhNDMxMGI5ODkyZTQzN2FlZDM2YjVlZGM3ZGU2NTEzYWM2MzM3In0.eyJhdWQiOiIxIiwianRpIjoiNjI5ZDllNzY3Y2QxYzJjNDRkMDUzMGI3MTZiMTYzM2JmMWI4NTAxMWQ1N2E0MzEwYjk4OTJlNDM3YWVkMzZiNWVkYzdkZTY1MTNhYzYzMzciLCJpYXQiOjE1NDA1MTMzMDEsIm5iZiI6MTU0MDUxMzMwMSwiZXhwIjoxNTcyMDQ5MzAxLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.OL2PcbO0xeGvIbXJtULK0OA0H2v-WTBfrLGAAZFgmgQDEtpUa9xg4MCZK7P1oHp2A_kHpe8QnV9TDQjI31mzSXrStI19i99D7zVCSQGBnAVWj3JD5-jp7IFOmZeypZhJrv08yzcnYApJIcdNo5HAWSBFrgceW-NVMA1aOohnyIZDGGlVMRlLHg6QNwn4YgcjyxCYop3xB3pFigGHuVfDdExfPqc61MiJtclQndncG19fThaHQjxyjfpSLonVp8VUSyibyonCMCrMtotoAGrOQaFZmCqCstEFJUz6g1ayCLTt28lPaUjzTG7-Q5LYUFL0DCu9O9wpw2aujGzcT6iX0KBx1JyGCwmbYqroZ9U7OK4bB5qwPXORHPAmOw9KTlfmdNLHp_nWGkiv7BtwskwPvYfilf9DHZLbFPvIIzqKKKFYWv9gYPdCZKz7-XwkGQgsezAtWq_PDwPELlUHYQ_ScsZWP0ALIQxH88pqEOlC4F4qQlVz3f-6gpHJi3yCMXQEF694ivP6Thc-I4QW54ICZC73LHxTg2LIHUA_BMwmGfCY-x5gwANuOSVhmSgFe7sbfggMQN2G6j16U7FtrNYK25GCslL8AMMwFG-i9DxaQKwzDnRKBYfbJQsfNJIOzy-HESGYLUD1unaMCWRh4icEXnj_OKp0qaA5XnbW_405Bpc");

        calendar_start.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                Day day = calendar_start.getSelectedDay();
                txt_start.setText(day.getDay() + "/" + day.getMonth() + 1 + "/" + day.getYear());
                Log.d("select : ", txt_start.getText().toString());
            }

            @Override
            public void onItemClick(View view) {

            }

            @Override
            public void onDataUpdate() {

            }

            @Override
            public void onMonthChange() {

            }

            @Override
            public void onWeekChange(int i) {

            }
        });
        calendar_start.setCalendarListener(new CalendaHelper(calendar_end, txt_end));
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
                store_hoatdong_sp_type.getSelectedItemPosition()
        );
        mPresenter.storeHoatDong("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjYyOWQ5ZTc2N2NkMWMyYzQ0ZDA1MzBiNzE2YjE2MzNiZjFiODUwMTFkNTdhNDMxMGI5ODkyZTQzN2FlZDM2YjVlZGM3ZGU2NTEzYWM2MzM3In0.eyJhdWQiOiIxIiwianRpIjoiNjI5ZDllNzY3Y2QxYzJjNDRkMDUzMGI3MTZiMTYzM2JmMWI4NTAxMWQ1N2E0MzEwYjk4OTJlNDM3YWVkMzZiNWVkYzdkZTY1MTNhYzYzMzciLCJpYXQiOjE1NDA1MTMzMDEsIm5iZiI6MTU0MDUxMzMwMSwiZXhwIjoxNTcyMDQ5MzAxLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.OL2PcbO0xeGvIbXJtULK0OA0H2v-WTBfrLGAAZFgmgQDEtpUa9xg4MCZK7P1oHp2A_kHpe8QnV9TDQjI31mzSXrStI19i99D7zVCSQGBnAVWj3JD5-jp7IFOmZeypZhJrv08yzcnYApJIcdNo5HAWSBFrgceW-NVMA1aOohnyIZDGGlVMRlLHg6QNwn4YgcjyxCYop3xB3pFigGHuVfDdExfPqc61MiJtclQndncG19fThaHQjxyjfpSLonVp8VUSyibyonCMCrMtotoAGrOQaFZmCqCstEFJUz6g1ayCLTt28lPaUjzTG7-Q5LYUFL0DCu9O9wpw2aujGzcT6iX0KBx1JyGCwmbYqroZ9U7OK4bB5qwPXORHPAmOw9KTlfmdNLHp_nWGkiv7BtwskwPvYfilf9DHZLbFPvIIzqKKKFYWv9gYPdCZKz7-XwkGQgsezAtWq_PDwPELlUHYQ_ScsZWP0ALIQxH88pqEOlC4F4qQlVz3f-6gpHJi3yCMXQEF694ivP6Thc-I4QW54ICZC73LHxTg2LIHUA_BMwmGfCY-x5gwANuOSVhmSgFe7sbfggMQN2G6j16U7FtrNYK25GCslL8AMMwFG-i9DxaQKwzDnRKBYfbJQsfNJIOzy-HESGYLUD1unaMCWRh4icEXnj_OKp0qaA5XnbW_405Bpc",
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
