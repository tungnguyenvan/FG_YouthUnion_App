package com.dev.nguyenvantung.fg_app.ui.hoatdong.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.ui.hoatdong.fragment.coming.HoatDongComingConstract;
import com.dev.nguyenvantung.fg_app.ui.hoatdong.fragment.finished.HoatDongFinishedConstract;
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
    private HoatDongComingConstract.View viewComming;
    private HoatDongFinishedConstract.View viewFinished;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_hoatdong_txt_name)
        TextView txt_name;
        @BindView(R.id.item_hoatdong_layout)
        public RelativeLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (viewComming != null) viewComming.hoatDongDetail(layout, listHoatDong.get(getAdapterPosition()).getId());
            else viewFinished.hoatDongDetail(layout, listHoatDong.get(getAdapterPosition()).getId());
        }
    }

    public HoatDongAdapter(List<HoatDong> listHoatDong, HoatDongComingConstract.View mView) {
        this.listHoatDong = listHoatDong;
        this.viewComming = mView;
    }

    public HoatDongAdapter(List<HoatDong> listHoatDong, HoatDongFinishedConstract.View mView){
        this.listHoatDong = listHoatDong;
        this.viewFinished = mView;
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
    }

    @Override
    public int getItemCount() {
        return listHoatDong.size();
    }
}
