package com.bw.movie.fragment.regionlist;


import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.areaadper.AreaAdper;
import com.bw.movie.adper.areaadper.RegionAdper;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.eventbean.AreaShow;
import com.bw.movie.bean.findCinemaByRegion.CinemaByRegionResult;
import com.bw.movie.bean.findHotMovieList.HomeShow;
import com.bw.movie.bean.findregion.FindRegionResult;
import com.bw.movie.prentent.FindRegionPreantent;
import com.bw.movie.prentent.area.AreaPrantent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegionFragment extends BaseFragment {

    @BindView(R.id.recy_area)
    RecyclerView recyArea;
    @BindView(R.id.recy_movie)
    RecyclerView recyMovie;
    @BindView(R.id.X)
    LinearLayout X;
    Unbinder unbinder;

    @Override
    protected int onLayout() {
        return R.layout.fragment_region;
    }

    @Override
    protected void onView(View view) {
        unbinder = ButterKnife.bind(this, view);
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onData() {
        FindRegionPreantent findRegionPreantent = new FindRegionPreantent(new MovieRegionCall());
        findRegionPreantent.getData();

        AreaPrantent areaPrantent = new AreaPrantent(new MovieAreaCall());
        areaPrantent.getData(1);
    }

    @Subscribe(sticky = true)
    public void show(AreaShow areaShow) {
        int regionId = areaShow.getRegionId();
        AreaPrantent areaPrantent = new AreaPrantent(new MovieAreaCall());
        areaPrantent.getData(regionId);
    }

    private class MovieRegionCall implements IBackCall<HomeShow<List<FindRegionResult>>> {
        @Override
        public void onSuccess(HomeShow<List<FindRegionResult>> homeShow) {
            List<FindRegionResult> result = homeShow.getResult();
            RegionAdper regionAdper = new RegionAdper();
            regionAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            recyArea.setLayoutManager(linearLayoutManager);
            recyArea.setAdapter(regionAdper);

        }

        @Override
        public void onFail(String mes) {

        }
    }

    private class MovieAreaCall implements IBackCall<HomeShow<List<CinemaByRegionResult>>> {
        @Override
        public void onSuccess(HomeShow<List<CinemaByRegionResult>> homeShow) {
            List<CinemaByRegionResult> result = homeShow.getResult();
            AreaAdper areaadper = new AreaAdper();
            areaadper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            recyMovie.setLayoutManager(linearLayoutManager);
            recyMovie.setAdapter(areaadper);
        }

        @Override
        public void onFail(String mes) {

        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
}
