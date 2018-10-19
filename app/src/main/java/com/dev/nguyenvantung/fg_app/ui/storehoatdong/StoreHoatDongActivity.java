package com.dev.nguyenvantung.fg_app.ui.storehoatdong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dev.nguyenvantung.fg_app.R;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import butterknife.BindView;

public class StoreHoatDongActivity extends AppCompatActivity
        implements StoreHoatDongConstact.Presenter, View.OnClickListener, CollapsibleCalendar.CalendarListener{
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.store_hoatdong_collapsing)
    public CollapsibleCalendar calendar;

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

    @Override
    public void onDaySelect() {

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
}
