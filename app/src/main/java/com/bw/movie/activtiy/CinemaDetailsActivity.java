package com.bw.movie.activtiy;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.activtiy.schedule.ScheduleActivity;
import com.bw.movie.adper.fragmentadper.FragmentCinemaAdper;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.cinemadetails.CinemaDetails;
import com.bw.movie.bean.eventbean.CinemaIdEven;
import com.bw.movie.bean.findHotMovieList.HomeShow;
import com.bw.movie.fragment.cinemadatail.CinemaFragment;
import com.bw.movie.fragment.cinemadatail.CommentFragment;
import com.bw.movie.prentent.cinemadetails.CinemaDetailPreantent;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CinemaDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_cm_name)
    TextView tvCmName;
    @BindView(R.id.tv_label)
    TextView tvLabel;
    @BindView(R.id.iv_cm_buck)
    ImageView ivCmBuck;
    @BindView(R.id.tab_cm)
    TabLayout tabCm;
    @BindView(R.id.vp_det)
    ViewPager vpDet;
    @BindView(R.id.but_schedule)
    Button butSchedule;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private Unbinder bind;

    @Override
    protected int onLayout() {
        return R.layout.activity_cinema_details;
    }

    @Override
    protected void onView() {
        bind = ButterKnife.bind(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onData() {
        fragments.add(new CinemaFragment());
        fragments.add(new CommentFragment());
        FragmentCinemaAdper fragmentCinemaAdper = new FragmentCinemaAdper(getSupportFragmentManager());
        fragmentCinemaAdper.addAll(fragments);
        vpDet.setAdapter(fragmentCinemaAdper);
        tabCm.setupWithViewPager(vpDet);
        tabCm.getTabAt(0).setText("影院详情");
        tabCm.getTabAt(1).setText("影院评价");
    }

    @Subscribe(sticky = true)
    public void Cinema(CinemaIdEven cinemaIdEven) {
        int cinemaId = cinemaIdEven.getCinemaId();
        CinemaDetailPreantent cinemaDetailPreantent = new CinemaDetailPreantent(new Cinema());
        cinemaDetailPreantent.getData(cinemaId);
    }

    @OnClick({R.id.iv_cm_buck,R.id.but_schedule})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_cm_buck:
                finish();
                break;
                case R.id.but_schedule:
                    Intent intent = new Intent(CinemaDetailsActivity.this, ScheduleActivity.class);
                    startActivity(intent);
                    break;
        }
    }

    private class Cinema implements IBackCall<HomeShow<CinemaDetails>> {
        @Override
        public void onSuccess(HomeShow<CinemaDetails> homeShow) {
            String name = homeShow.getResult().getName();
            tvCmName.setText(name);
            String label = homeShow.getResult().getLabel();
            tvLabel.setText(label);
        }

        @Override
        public void onFail(String mes) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        EventBus.getDefault().unregister(this);
    }
}
