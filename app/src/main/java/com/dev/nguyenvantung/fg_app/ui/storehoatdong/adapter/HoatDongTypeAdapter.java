package com.dev.nguyenvantung.fg_app.ui.storehoatdong.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.hoatdongtype.HoatDongType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HoatDongTypeAdapter extends BaseAdapter {
    @BindView(R.id.item_hdt_txt_name)
    public TextView item_hdt_txt_name;
    private List<HoatDongType> hoatDongTypes;
    private Activity activity;
    private LayoutInflater inflater;

    public HoatDongTypeAdapter(List<HoatDongType> hoatDongTypes, Activity activity) {
        this.hoatDongTypes = hoatDongTypes;
        this.activity = activity;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return hoatDongTypes.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null){
            view = inflater.inflate(R.layout.item_sp_hoatdongtype, null);
        }
        ButterKnife.bind(this, view);
        item_hdt_txt_name.setText(hoatDongTypes.get(position).getType());
        return view;
    }
}
