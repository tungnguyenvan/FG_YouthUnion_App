package com.dev.nguyenvantung.fg_app.ui.main.fragment.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.ui.hoatdongdetail.HoatDongDetailActivity;
import com.dev.nguyenvantung.fg_app.utils.helper.DateHelper;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HoatDongAdapter extends RecyclerView.Adapter<HoatDongAdapter.ViewHolder> {
    private static final String TAG = "HoatDongAdapter ";
    private List<HoatDong> listHoatDong;
    private SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
    private DateHelper dateHelper = new DateHelper();
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_hoatdong_txt_name)
        TextView txt_name;
        @BindView(R.id.item_hoatdong_txt_desc)
        TextView txt_desc;
        @BindView(R.id.item_hoatdong_txt_from_date)
        TextView txt_from_date;
        @BindView(R.id.item_hoatdong_txt_to_date)
        TextView txt_to_date;
        @BindView(R.id.item_hoatdong_layout)
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, HoatDongDetailActivity.class);
            Pair[] pairs = new Pair[1];
            pairs[0] = new Pair(layout, context.getResources().getString(R.string.share_hoatdong));
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context, pairs);
            context.startActivity(intent, options.toBundle());
        }
    }

    public HoatDongAdapter(List<HoatDong> listHoatDong, Context context) {
        this.listHoatDong = listHoatDong;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_hoatdong, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HoatDong hoatDong = listHoatDong.get(i);
        String strDate = fm.format(hoatDong.getFromDate());
        viewHolder.txt_name.setText(hoatDong.getName());
        viewHolder.txt_desc.setText(hoatDong.getDesc());
        DateHelper.date date = DateHelper.getInstance();
        date = dateHelper.splitDate(strDate);
        viewHolder.txt_from_date.setText(date.getDay() + "/" + date.getMonth());
        viewHolder.txt_to_date.setText(this.fm.format(hoatDong.getEndDate()));
    }

    @Override
    public int getItemCount() {
        return listHoatDong.size();
    }
}
