package com.dev.nguyenvantung.fg_app.ui.lcdoandetail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.lcdoan.LCDoan;
import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.dev.nguyenvantung.fg_app.ui.lcdoan.adapter.LCDoanAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LCDoanDetailAdapter extends RecyclerView.Adapter<LCDoanDetailAdapter.ViewHolder>{

    List<User> listUser;

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_lcdoan_detail_txt_username)
        TextView txt_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public LCDoanDetailAdapter (List<User> listUser) {
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LCDoanDetailAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_lcdoan_detail, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.txt_name.setText(listUser.get(i).getUsername());
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

}
