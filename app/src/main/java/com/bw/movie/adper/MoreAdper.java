package com.bw.movie.adper;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MoreAdper extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments=new ArrayList<>();
    public void addAll(ArrayList<Fragment> fragments){
        this.fragments.addAll(fragments);
    }
    public MoreAdper(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
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
