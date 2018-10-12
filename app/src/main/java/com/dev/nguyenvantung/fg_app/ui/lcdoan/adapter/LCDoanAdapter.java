package com.dev.nguyenvantung.fg_app.ui.lcdoan.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LCDoanAdapter extends RecyclerView.Adapter<LCDoanAdapter.ViewHolder> {
    private List<LCDoan> listLCDoan;

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_name)
        public TextView txt_name;
        @BindView(R.id.txt_desc)
        public TextView txt_desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public LCDoanAdapter(List<LCDoan> listLCDoan){
        this.listLCDoan = listLCDoan;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lcdoan, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txt_name.setText(listLCDoan.get(i).getName());
        viewHolder.txt_desc.setText(listLCDoan.get(i).getDesc());
    }

    @Override
    public int getItemCount() {
        return listLCDoan.size();
    }
}
