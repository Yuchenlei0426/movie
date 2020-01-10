package com.bw.movie.adper.cinemadate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class DateFragmentAdper extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments=new ArrayList<>();

    public DateFragmentAdper(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }
    public void addAll(ArrayList<Fragment> fragments){
        this.fragments.addAll(fragments);

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
