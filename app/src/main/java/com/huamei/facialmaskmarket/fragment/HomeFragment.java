package com.huamei.facialmaskmarket.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huamei.facialmaskmarket.R;

/**
 * Use:
 * Author:陈懿鹏
 * Data:2017/4/12.
 */

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_pager_layout,null);
        return view;

    }
}
