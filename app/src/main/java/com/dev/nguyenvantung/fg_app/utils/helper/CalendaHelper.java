package com.dev.nguyenvantung.fg_app.utils.helper;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

public class CalendaHelper implements CollapsibleCalendar.CalendarListener {
    private CollapsibleCalendar collapsibleCalendar;
    private TextView textView;

    public CalendaHelper(CollapsibleCalendar collapsibleCalendar,TextView textView){
        this.collapsibleCalendar = collapsibleCalendar;
        this.textView = textView;
    }

    @Override
    public void onDaySelect() {
        Day day = collapsibleCalendar.getSelectedDay();
        int month = day.getMonth() + 1;

        if (month < 10) {
            textView.setText(day.getYear() + "-0" + month + "-" + day.getDay());
        } else {
            textView.setText(day.getYear() + "-" + month + "-" + day.getDay());
        }

        Log.d("select : ", textView.getText().toString());
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
