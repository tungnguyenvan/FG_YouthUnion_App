package com.dev.nguyenvantung.fg_app.ui.storehoatdong;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import com.dev.nguyenvantung.fg_app.ui.main.fragment.adapter.HoatDongAdapter;
import com.dev.nguyenvantung.fg_app.ui.storehoatdong.adapter.HoatDongTypeAdapter;
import com.dev.nguyenvantung.fg_app.utils.helper.DateHelper;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreHoatDongActivity extends AppCompatActivity
        implements StoreHoatDongConstact.View, View.OnClickListener{
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
        mPresenter.listHoatDongType("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjZhNzA3Mzk0ZWI3MWJmOTE3YTJlMTViYzNhM2M1YTMzNDZmNWNmZTkxYzIxNTc5ZDA2NWI4MzQ4OWY4M2ZlMGJiMmMyYTgwOWU5ZTI1NzExIn0.eyJhdWQiOiIxIiwianRpIjoiNmE3MDczOTRlYjcxYmY5MTdhMmUxNWJjM2EzYzVhMzM0NmY1Y2ZlOTFjMjE1NzlkMDY1YjgzNDg5ZjgzZmUwYmIyYzJhODA5ZTllMjU3MTEiLCJpYXQiOjE1NDA0Njk3OTIsIm5iZiI6MTU0MDQ2OTc5MiwiZXhwIjoxNTcyMDA1NzkyLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.p36L-zUohoNQzko6rRc834-h4ME2_2yIy8Llq3xMeCdPsS_BP3ia3K39Vqmhw7BYIYd8aY-ycfj9sAJEGDb6EeH6rhSPjqM0aL5G7UnYZTU3Nxj-m5UTfr__C65rX52JJPFJJgS3XcjdvVPUW1k5fGA-MzUg_bBlKW-9BjPiguJvqHftC_6mkBrA6WtzgP7J1BEVb1nkTLJpe9BawLyD_F59bq6bWffjad3f5Lg4myn1HMto-DZDNPYfKWKkwvMYu9XqBfBWQTwtxZgnYB7QxV6v5pVIiAzX66Q0QJQRXoiTe0F5uXWBoVIr0izSQX7T3ybPhEGqeawC0Jp6G9sdfV2_RwRBLPvWW-joUYqwZrzp8I-Fbg0XVmSnqYEfqrBHUaMOZCUUl2PpybhdHUBTPSqdp1Togu1DGbEGPLOXS3r-HdlWqeHuBRh7PA8582GSXvyVJFpEehG0YSsVPwuGVsX8LBKsIubSgV8O5UZBdpKodU8ZvgT1t21pZoLI6Ipsc3hlboSIHvWTThA8VfQTrt-IjxR28jjpJNL0K4_-1dRkJRfsNvF78XJVwF1eOUOlTPu7_GRJ1x2lB9wInlOBaWn5ChUFgjrvZykfkX57F-7iVEMRdaMLP3Gb4oLMyztFWZOprF_5RXahAeTXmiWcvOmVeBPJ-JaWipP7gwFEyBw");
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
                "2018-10-24",
                "2018-10-25",
                store_hoatdong_sp_type.getSelectedItemPosition()
        );
        mPresenter.storeHoatDong("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjZhNzA3Mzk0ZWI3MWJmOTE3YTJlMTViYzNhM2M1YTMzNDZmNWNmZTkxYzIxNTc5ZDA2NWI4MzQ4OWY4M2ZlMGJiMmMyYTgwOWU5ZTI1NzExIn0.eyJhdWQiOiIxIiwianRpIjoiNmE3MDczOTRlYjcxYmY5MTdhMmUxNWJjM2EzYzVhMzM0NmY1Y2ZlOTFjMjE1NzlkMDY1YjgzNDg5ZjgzZmUwYmIyYzJhODA5ZTllMjU3MTEiLCJpYXQiOjE1NDA0Njk3OTIsIm5iZiI6MTU0MDQ2OTc5MiwiZXhwIjoxNTcyMDA1NzkyLCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.p36L-zUohoNQzko6rRc834-h4ME2_2yIy8Llq3xMeCdPsS_BP3ia3K39Vqmhw7BYIYd8aY-ycfj9sAJEGDb6EeH6rhSPjqM0aL5G7UnYZTU3Nxj-m5UTfr__C65rX52JJPFJJgS3XcjdvVPUW1k5fGA-MzUg_bBlKW-9BjPiguJvqHftC_6mkBrA6WtzgP7J1BEVb1nkTLJpe9BawLyD_F59bq6bWffjad3f5Lg4myn1HMto-DZDNPYfKWKkwvMYu9XqBfBWQTwtxZgnYB7QxV6v5pVIiAzX66Q0QJQRXoiTe0F5uXWBoVIr0izSQX7T3ybPhEGqeawC0Jp6G9sdfV2_RwRBLPvWW-joUYqwZrzp8I-Fbg0XVmSnqYEfqrBHUaMOZCUUl2PpybhdHUBTPSqdp1Togu1DGbEGPLOXS3r-HdlWqeHuBRh7PA8582GSXvyVJFpEehG0YSsVPwuGVsX8LBKsIubSgV8O5UZBdpKodU8ZvgT1t21pZoLI6Ipsc3hlboSIHvWTThA8VfQTrt-IjxR28jjpJNL0K4_-1dRkJRfsNvF78XJVwF1eOUOlTPu7_GRJ1x2lB9wInlOBaWn5ChUFgjrvZykfkX57F-7iVEMRdaMLP3Gb4oLMyztFWZOprF_5RXahAeTXmiWcvOmVeBPJ-JaWipP7gwFEyBw",
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
    public void createHoatDongSuccess(HoatDong hoatDong) {
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
