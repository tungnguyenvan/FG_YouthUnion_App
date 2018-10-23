package com.dev.nguyenvantung.fg_app.ui.lcdoan.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;
import com.dev.nguyenvantung.fg_app.ui.lcdoan.LCDoanConstact;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LCDoanAdapter extends RecyclerView.Adapter<LCDoanAdapter.ViewHolder> {
    private List<LCDoan> listLCDoan;
    private static final String TAG = LCDoanAdapter.class.getName();
    private String keyName = "NAME";
    private String keyDesc = "DESC";
    private LCDoanConstact.View mView;

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_lcdoan_card)
        CardView cardView;
        @BindView(R.id.item_lcdoan_txt_name)
        public TextView txt_name;
        @BindView(R.id.item_lcdoan_txt_desc)
        public TextView txt_desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public LCDoanAdapter(List<LCDoan> listLCDoan, LCDoanConstact.View view){
        this.listLCDoan = listLCDoan;
        this.mView = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_rv_lcdoan, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LCDoan lcDoan = listLCDoan.get(i);
        viewHolder.txt_name.setText(lcDoan.getName());
        viewHolder.txt_desc.setText(lcDoan.getDesc());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, lcDoan.getName());
                mView.nextPage(lcDoan.getId());
            }

        });
    }

    @Override
    public int getItemCount() {
        return listLCDoan.size();
    }
}
