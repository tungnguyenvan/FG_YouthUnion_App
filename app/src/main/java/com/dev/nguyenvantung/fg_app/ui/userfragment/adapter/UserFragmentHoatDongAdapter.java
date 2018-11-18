package com.dev.nguyenvantung.fg_app.ui.userfragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.model.userhoatdong.UserHoatDong;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserFragmentHoatDongAdapter extends RecyclerView.Adapter<UserFragmentHoatDongAdapter.Viewholder> {
    private List<UserHoatDong> mUserHoatDongs;
    private Context mContext;

    public UserFragmentHoatDongAdapter(List<UserHoatDong> mUserHoatDongs, Context mContext) {
        this.mUserHoatDongs = mUserHoatDongs;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserFragmentHoatDongAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Viewholder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_hoatdong_user_fragment, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserFragmentHoatDongAdapter.Viewholder viewholder, int i) {
        viewholder.txtName.setText(mUserHoatDongs.get(i).getHoatdong().getName());
    }

    @Override
    public int getItemCount() {
        return mUserHoatDongs.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_userfragment_name)
        public TextView txtName;

        @BindView(R.id.item_userfragment_img)
        public ImageView image;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
