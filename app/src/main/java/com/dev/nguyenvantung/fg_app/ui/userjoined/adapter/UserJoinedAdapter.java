package com.dev.nguyenvantung.fg_app.ui.userjoined.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.userhoatdong.UserHoatDong;
import com.dev.nguyenvantung.fg_app.ui.userjoined.UserJoinedContract;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserJoinedAdapter extends RecyclerView.Adapter<UserJoinedAdapter.ViewHolder> {
    private List<UserHoatDong> userHoatDongs;
    private UserJoinedContract.View mView;

    public UserJoinedAdapter(List<UserHoatDong> userHoatDongs, UserJoinedContract.View mView){
        this.userHoatDongs = userHoatDongs;
        this.mView = mView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_joined, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtUserName.setText(userHoatDongs.get(i).getUser().getUsername());
    }

    @Override
    public int getItemCount() {
        return userHoatDongs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_user_name)
        public TextView txtUserName;

        @BindView(R.id.item_joined_layout)
        public RelativeLayout mLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mLayout.setOnClickListener(view -> showUser());
        }

        private void showUser(){
            mView.showUser(userHoatDongs.get(getAdapterPosition()).getUserId());
        }
    }
}
