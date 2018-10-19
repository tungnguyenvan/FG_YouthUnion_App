package com.dev.nguyenvantung.fg_app.ui.storehoatdong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import butterknife.BindView;

public class StoreHoatDongActivity extends AppCompatActivity
        implements StoreHoatDongConstact.Presenter, View.OnClickListener{
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
    @BindView(R.id.store_hoatdong_sp_hocky)
    public Spinner sp_hocky;
    @BindView(R.id.store_hoatdong_sp_type)
    public Spinner sp_type;
    @BindView(R.id.store_hoatdong_ed_desc)
    public EditText ed_desc;
    @BindView(R.id.store_hoatdong_btn_create)
    public Button btn_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_hoat_dong);

        initView();
    }

    private void initView() {

    }

    @Override
    public void listHoatDongType(String token) {

    }

    @Override
    public void onClick(View v) {

    }
}
