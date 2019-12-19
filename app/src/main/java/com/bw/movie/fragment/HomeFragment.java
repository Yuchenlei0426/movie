package com.bw.movie.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activtiy.move.MoreActivity;
import com.bw.movie.adper.ComingSoonAdper;
import com.bw.movie.adper.HotItemAdper;
import com.bw.movie.adper.ReleaseAdper;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.banner.BannerBean;
import com.bw.movie.bean.banner.BannerShow;
import com.bw.movie.bean.findComingSoonMovieList.ComingSoonShow;
import com.bw.movie.bean.findComingSoonMovieList.FindComingSoonMovieList;
import com.bw.movie.bean.findHotMovieList.HomeShow;
import com.bw.movie.bean.findHotMovieList.MovieResult;
import com.bw.movie.bean.findReleaseMovieList.FindReleaseMovieList;
import com.bw.movie.bean.findReleaseMovieList.ReleaseShow;
import com.bw.movie.prentent.HomeBannerPrentent;
import com.bw.movie.prentent.HomeComingSoonPrantent;
import com.bw.movie.prentent.HomeHotPrentent;
import com.bw.movie.prentent.HomeReleasePrantent;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";


    Unbinder unbinder;
    @BindView(R.id.x_banner)
    XBanner xBanner;
    @BindView(R.id.iv_xin)
    ImageView ivXin;
    @BindView(R.id.re_move1)
    TextView reMove1;
    @BindView(R.id.rv_hot)
    RecyclerView rvHot;
    @BindView(R.id.iv_hotre)
    ImageView ivHotre;
    @BindView(R.id.re_move3)
    TextView reMove3;
    @BindView(R.id.iv_pic)
    SimpleDraweeView ivPic;
    @BindView(R.id.rv_release)
    RecyclerView rvRelease;
    @BindView(R.id.iv_hot)
    ImageView ivHot;
    @BindView(R.id.re_move2)
    TextView reMove2;
    @BindView(R.id.rv_coming_soon)
    RecyclerView rvComingSoon;

    @Override
    protected int onLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    protected void onData() {
        //banner
        HomeBannerPrentent homeBannerPrentent = new HomeBannerPrentent(new BannerCall());
        homeBannerPrentent.getData();
        //热门电影
        HomeHotPrentent homePrentent = new HomeHotPrentent(new HomehotCall());
        homePrentent.getData(1, 5);

        //    即将上映
        HomeComingSoonPrantent homeComingSoonPrantent = new HomeComingSoonPrantent(new ComingSoonCall());
        homeComingSoonPrantent.getData(1, 5);
        //    正在热映
        HomeReleasePrantent homeReleasePrantent = new HomeReleasePrantent(new ReleaseCall());
        homeReleasePrantent.getData(1, 5);

    }

    @OnClick({R.id.re_move1, R.id.re_move2, R.id.re_move3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.re_move1:
                Intent intent1 = new Intent(getActivity(), MoreActivity.class);
                intent1.putExtra("fragment_flag", 3);
                startActivity(intent1);
                break;
            case R.id.re_move2:
                Intent intent2 = new Intent(getActivity(), MoreActivity.class);
                intent2.putExtra("fragment_flag", 3);
                startActivity(intent2);
                ;break;
            case R.id.re_move3:
                Intent intent3 = new Intent(getActivity(), MoreActivity.class);
                intent3.putExtra("fragment_flag", 3);
                startActivity(intent3);
                ; break;
        }
    }



    //banner
    private class BannerCall implements IBackCall<BannerBean> {

        @Override
        public void onSuccess(BannerBean homeShow) {
            final List<BannerShow> bannerShows = homeShow.getResult();
            xBanner.setData(bannerShows, null);
            xBanner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, View view, int position) {
                    Glide.with(view.getContext()).load(bannerShows.get(position).getImageUrl()).into((ImageView) view);
                }
            });

        }

        @Override
        public void onFail(String mes) {
            Log.i(TAG, "onFail: " + mes);

        }
    }


    //正在热映
    private class HomehotCall implements IBackCall<HomeShow> {

        @Override
        public void onSuccess(HomeShow homeShow) {
            List<MovieResult> hotResult = homeShow.getResult();
            String imageUrl = hotResult.get(0).getHorizontalImage();
            ivPic.setImageURI(imageUrl);
            HotItemAdper hotItemAdper = new HotItemAdper();
            hotItemAdper.addAll(hotResult);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

            rvHot.setLayoutManager(linearLayoutManager);
            rvHot.setAdapter(hotItemAdper);
        }

        @Override
        public void onFail(String mes) {

        }
    }

    //    即将上映
    private class ComingSoonCall implements IBackCall<ComingSoonShow> {
        @Override
        public void onSuccess(ComingSoonShow homeShow) {
            Log.d("yclmovie", homeShow.getMessage() + "");
            List<FindComingSoonMovieList> comingSoonResult = homeShow.getResult();
            ComingSoonAdper comingSoonAdper = new ComingSoonAdper();
            comingSoonAdper.addAll(comingSoonResult);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            rvComingSoon.setLayoutManager(linearLayoutManager);
            rvComingSoon.setAdapter(comingSoonAdper);

        }

        @Override
        public void onFail(String mes) {
            Log.d("yclmovie", mes);
        }
    }


    private class ReleaseCall implements IBackCall<ReleaseShow> {
        @Override
        public void onSuccess(ReleaseShow homeShow) {
            List<FindReleaseMovieList> releaseResult = homeShow.getResult();
            Log.i(TAG, "onSuccess: " + homeShow.getMessage());

            ReleaseAdper releaseAdper = new ReleaseAdper();
            releaseAdper.addAll(releaseResult);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
            rvRelease.setLayoutManager(linearLayoutManager);
            rvRelease.setAdapter(releaseAdper);

        }

        @Override
        public void onFail(String mes) {
            Log.i(TAG, "onFail: " + mes);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
