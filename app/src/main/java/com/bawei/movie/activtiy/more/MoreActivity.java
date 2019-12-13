package com.bawei.movie.activtiy.more;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import android.widget.ImageView;
import com.bawei.movie.adper.MoreAdper;
import com.bawei.movie.base.BaseActivity;
import com.bawei.movie.fragment.movefragment.MoreComingSoonFragment;
import com.bawei.movie.fragment.movefragment.MoreHotFragment;
import com.bawei.movie.fragment.movefragment.MoreReleaseFragment;
import com.bw.movie.R;

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
