package com.bw.movie.adper.cinema;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class CinemaPagerAdper extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    public CinemaPagerAdper(FragmentManager childFragmentManager) {
        super(childFragmentManager);
    }
    public void  addAll(ArrayList<Fragment> fragments){
        this.fragments=fragments;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
