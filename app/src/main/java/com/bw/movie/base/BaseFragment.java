package com.bw.movie.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public  abstract class BaseFragment extends Fragment {
//    初始化  视图初始化
    private boolean isViewInitialization;
    private boolean isVisible;
    private boolean isInitialization;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(onLayout(), container, false);
        onView(view);
        onData();

        return view;
    }

    protected abstract int onLayout();

    protected abstract void onView(View view);

    protected abstract void onData();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

    }
}
