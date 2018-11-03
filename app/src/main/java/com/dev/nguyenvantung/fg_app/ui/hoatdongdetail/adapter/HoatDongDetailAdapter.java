package com.dev.nguyenvantung.fg_app.ui.hoatdongdetail.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.user.User;
import com.dev.nguyenvantung.fg_app.ui.hoatdongdetail.HoatDongDetailContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HoatDongDetailAdapter extends RecyclerView.Adapter<HoatDongDetailAdapter.ViewHolder> {
    private List<User> mUsers;
    private HoatDongDetailContract.View mView;

    public HoatDongDetailAdapter(List<User> mUsers, HoatDongDetailContract.View mView) {
        this.mUsers = mUsers;
        this.mView = mView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user_checin, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtName.setText(mUsers.get(i).getUsername());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_user_name)
        public TextView txtName;
        @BindView(R.id.item_user_checkin)
        public Button btnCheckin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            btnCheckin.setOnClickListener(view -> checkIn());
        }
        private void checkIn(){
            mView.checkIn(getAdapterPosition());
        }
    }
}
