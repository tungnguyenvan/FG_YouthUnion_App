package com.dev.nguyenvantung.fg_app.ui.main.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.ui.lcdoan.adapter.LCDoanAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HoatDongAdapter extends RecyclerView.Adapter<HoatDongAdapter.ViewHolder>{
    private List<HoatDong> listHoatDong;
    private SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
    private Date currentDate = Calendar.getInstance().getTime();
    private String valid_until = "1-2-1990";
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_hoatdong_txt_name)
        TextView txt_name;
        @BindView(R.id.item_hoatdong_txt_desc)
        TextView txt_desc;
        @BindView(R.id.item_hoatdong_txt_date)
        TextView txt_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public HoatDongAdapter(List<HoatDong> listHoatDong) {
        this.listHoatDong = listHoatDong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_hoatdong, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.txt_name.setText(listHoatDong.get(i).getName());
        viewHolder.txt_desc.setText(listHoatDong.get(i).getDesc());
        viewHolder.txt_date.setText("Từ ngày " + splitDate(listHoatDong.get(i).getFromDate()) +
                " Đến ngày " + splitDate(listHoatDong.get(i).getEndDate()));


    }

    private String splitDate(Date date) {
        String strDate = this.fm.format(date);
        return strDate;
    }

    @Override
    public int getItemCount() {
        return listHoatDong.size();
    }
}
