package com.bw.movie.activtiy.schedule;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.findHotMovieList.HomeShow;
import com.bw.movie.adper.cinemadate.DateFragmentAdper;
import com.bw.movie.fragment.date.DateScheduleFragment;
import com.bw.movie.prentent.DatePreantent;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleActivity extends BaseActivity {
    private static final String TAG = "ScheduleActivity";
    @BindView(R.id.tab_schedule)
    TabLayout tabSchedule;
    @BindView(R.id.vp_schedule)
    ViewPager vpSchedule;

    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected int onLayout() {
        return R.layout.activity_schedule;
    }

    @Override
    protected void onView() {
        ButterKnife.bind(this);
        vpSchedule.setOffscreenPageLimit(0);
    }

    @Override
    protected void onData() {
        DatePreantent datePreantent = new DatePreantent(new DateCall());
        datePreantent.getData();
    }



    private class DateCall implements IBackCall<HomeShow<List<String>>> {
        @Override
        public void onSuccess(HomeShow<List<String>> homeShow) {
            List<String> result = homeShow.getResult();

            for (int i = 0; i < result.size(); i++) {
                String s = result.get(i);
                fragments.add(new DateScheduleFragment(s));
            }
            DateFragmentAdper dateFragmentAdper = new DateFragmentAdper(getSupportFragmentManager());
            dateFragmentAdper.addAll(fragments);
            vpSchedule.setAdapter(dateFragmentAdper);
            tabSchedule.setupWithViewPager(vpSchedule);
            for (int i = 0; i < result.size(); i++) {
                String s = result.get(i);
                tabSchedule.getTabAt(i).setText(s);
            }

        }

        @Override
        public void onFail(String mes) {
            Log.e(TAG, "onFail: " + mes);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
