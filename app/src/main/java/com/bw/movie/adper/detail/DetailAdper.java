package com.bw.movie.adper.detail;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class DetailAdper extends FragmentPagerAdapter {
    public DetailAdper(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }
   private ArrayList<Fragment> fragments;
    public void addAll(ArrayList<Fragment> fragments){
        this.fragments=fragments;
    }
    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
