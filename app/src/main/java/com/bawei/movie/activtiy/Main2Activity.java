package com.bawei.movie.activtiy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bawei.movie.R;
import com.bawei.movie.fragment.HomeFragment;
import com.bawei.movie.fragment.MineFragment;
import com.bawei.movie.fragment.ShowFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.tv_moview)
    TextView tvMoview;
    @BindView(R.id.lin_movie)
    LinearLayout linMovie;
    @BindView(R.id.movie_ima)
    ImageView movieIma;
    @BindView(R.id.relay_view1)
    RelativeLayout relayView1;
    @BindView(R.id.tv_cinema)
    TextView tvCinema;
    @BindView(R.id.lin_cinema)
    LinearLayout linCinema;
    @BindView(R.id.cinema_ima)
    ImageView cinemaIma;
    @BindView(R.id.relay_view2)
    RelativeLayout relayView2;
    @BindView(R.id.tv_mine)
    TextView tvMine;
    @BindView(R.id.lin_mine)
    LinearLayout linMine;
    @BindView(R.id.mine_ima)
    ImageView mineIma;
    @BindView(R.id.relay_view3)
    RelativeLayout relayView3;
    private HomeFragment homeFragment;
    private ShowFragment showFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        homeFragment = new HomeFragment();
        showFragment = new ShowFragment();
        mineFragment = new MineFragment();
        // 显示隐藏
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame, homeFragment)
                .add(R.id.frame, showFragment)
                .add(R.id.frame, mineFragment)
                .show(homeFragment)
                .hide(showFragment)
                .hide(mineFragment)
                .commit();
        linMovie.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.movie_ima, R.id.cinema_ima, R.id.mine_ima})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.movie_ima:
                getSupportFragmentManager()
                        .beginTransaction()
                        .show(homeFragment)
                        .hide(showFragment)
                        .hide(mineFragment)
                        .commit();
                linMovie.setVisibility(View.VISIBLE);
                linCinema.setVisibility(View.GONE);
                linMine.setVisibility(View.GONE);
                movieIma.setVisibility(View.GONE);
                cinemaIma.setVisibility(View.VISIBLE);
                mineIma.setVisibility(View.VISIBLE);

                break;
            case R.id.cinema_ima:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(homeFragment)
                        .show(showFragment)
                        .hide(mineFragment)
                        .commit();
                linMovie.setVisibility(View.GONE);
                linCinema.setVisibility(View.VISIBLE);
                linMine.setVisibility(View.GONE);
                movieIma.setVisibility(View.VISIBLE);
                cinemaIma.setVisibility(View.GONE);
                mineIma.setVisibility(View.VISIBLE);

                break;
            case R.id.mine_ima:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(homeFragment)
                        .hide(showFragment)
                        .show(mineFragment)
                        .commit();
                linMovie.setVisibility(View.GONE);
                linCinema.setVisibility(View.GONE);
                linMine.setVisibility(View.VISIBLE);
                movieIma.setVisibility(View.VISIBLE);
                cinemaIma.setVisibility(View.VISIBLE);
                mineIma.setVisibility(View.GONE);
                break;
        }
    }
}
