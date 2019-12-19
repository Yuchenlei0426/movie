package com.bw.movie.activtiy.move;


import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.adper.MoreAdper;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.fragment.movefragment.MoreComingSoonFragment;
import com.bw.movie.fragment.movefragment.MoreHotFragment;
import com.bw.movie.fragment.movefragment.MoreReleaseFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreActivity extends BaseActivity {


    @BindView(R.id.buck)
    ImageView buck;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tab_more)
    TabLayout tabMore;
    @BindView(R.id.vp_more)
    ViewPager vpMore;

    private ArrayList<Fragment> fragments =new ArrayList<>();
    @Override
    protected int onLayout() {
        return R.layout.activity_more;
    }

    @Override
    protected void onView() {
        ButterKnife.bind(this);

    }

    @Override
    protected void onData() {
        MoreAdper moreAdper = new MoreAdper(getSupportFragmentManager());
        fragments.add(new MoreReleaseFragment());
        fragments.add(new MoreComingSoonFragment());
        fragments.add(new MoreHotFragment());
        moreAdper.addAll(fragments);
        vpMore.setAdapter(moreAdper);
        tabMore.setupWithViewPager(vpMore);
        tabMore.getTabAt(0).setText("正在热映");
        tabMore.getTabAt(1).setText("即将上映");
        tabMore.getTabAt(2).setText("热门电影");



    }




    @OnClick(R.id.buck)
    public void onClick() {
        finish();
    }
}
