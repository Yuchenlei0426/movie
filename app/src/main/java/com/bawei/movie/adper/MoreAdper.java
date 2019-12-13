package com.bawei.movie.adper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
