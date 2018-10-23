package com.dev.nguyenvantung.fg_app.ui.lcdoan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.khoa.Khoa;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LCDoanSpinnerAdapter extends BaseAdapter{

    private Context context;
    private List<Khoa> listKhoa;
    LayoutInflater inflater;
    @BindView(R.id.item_lcdoan_txt_khoa)
    public TextView txt_khoa;

    public LCDoanSpinnerAdapter(Context context, List<Khoa> listKhoa) {
        this.context = context;
        this.listKhoa = listKhoa;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listKhoa.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return listKhoa.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_sp_lcdoan, null);
        ButterKnife.bind(this, view);

        Khoa khoa= listKhoa.get(i);
        txt_khoa.setText(khoa.getName());
        return view;
    }
}
