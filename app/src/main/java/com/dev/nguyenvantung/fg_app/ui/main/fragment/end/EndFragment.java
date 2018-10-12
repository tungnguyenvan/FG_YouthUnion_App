package com.dev.nguyenvantung.fg_app.ui.main.fragment.end;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dev.nguyenvantung.fg_app.R;
import com.dev.nguyenvantung.fg_app.data.model.hoatdong.HoatDong;
import com.dev.nguyenvantung.fg_app.data.repository.HoatDongRepository;
import com.dev.nguyenvantung.fg_app.data.source.local.HoatDongLocalDataSource;
import com.dev.nguyenvantung.fg_app.data.source.remote.HoatDongRemoteDataSource;
import com.dev.nguyenvantung.fg_app.ui.main.fragment.hoatdong.HoatDongConstract;
import com.dev.nguyenvantung.fg_app.ui.main.fragment.hoatdong.HoatDongPresenter;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.dev.nguyenvantung.fg_app.utils.rx.SchedulerProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EndFragment extends Fragment implements HoatDongConstract.View{
    private static EndFragment instance;
    private static final String TAG = "EndFragment: ";

    private HoatDongConstract.Presenter mPresenter;

    public EndFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance(){
        if (instance == null){
            instance = new EndFragment();
        }
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_end, container, false);
        ButterKnife.bind(this, view);

        AppPref.getInstance(getContext()).putApiToken(AppConstants.TOKEN);
        HoatDongRepository hoatDongRepository = new HoatDongRepository(HoatDongLocalDataSource.getInstance(),
                HoatDongRemoteDataSource.getInstance(getContext()));
        mPresenter = new HoatDongPresenter(hoatDongRepository, SchedulerProvider.getInstance());

        mPresenter.listHoatDong(AppConstants.BEARER + AppPref.getInstance(getContext()).getApiToken());

        return view;
    }

    @Override
    public void setListHoatDong(List<HoatDong> listHoatDong) {
        Log.d(TAG, listHoatDong.size()+"");
    }
}
