package com.bw.movie.fragment.cinema;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.areaadper.AreaAdper;
import com.bw.movie.adper.areaadper.RegionAdper;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.eventbean.AreaShow;
import com.bw.movie.bean.findCinemaByRegion.CinemaByRegion;
import com.bw.movie.bean.findCinemaByRegion.CinemaByRegionResult;
import com.bw.movie.bean.findregion.FindRegionResult;
import com.bw.movie.bean.findregion.FindRegionShow;
import com.bw.movie.prentent.FindRegionPreantent;
import com.bw.movie.prentent.area.AreaPrantent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class AreaFragment extends BaseFragment {
    private static final String TAG = "AreaFragment";

    @BindView(R.id.recy1_area)
    RecyclerView recy1Area;
    @BindView(R.id.recy2_area)
    RecyclerView recy2Area;
    @BindView(R.id.Y)
    LinearLayout Y;
    Unbinder unbinder;

    @Override
    protected int onLayout() {
        return R.layout.fragment_area;
    }

    @Override
    protected void onView(View view) {
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onData() {
        FindRegionPreantent findRegionPreantent = new FindRegionPreantent(new RegionCall());
        findRegionPreantent.getData();

        AreaPrantent areaPrantent = new AreaPrantent(new AreaCall());
        areaPrantent.getData(1);
    }
    @Subscribe(sticky = true)
    public void  show(AreaShow areaShow){
        int regionId = areaShow.getRegionId();
        AreaPrantent areaPrantent = new AreaPrantent(new AreaCall());
        areaPrantent.getData(regionId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    private class RegionCall implements IBackCall<FindRegionShow> {
        @Override
        public void onSuccess(FindRegionShow homeShow) {
            List<FindRegionResult> result = homeShow.getResult();
            RegionAdper regionAdper = new RegionAdper();
            regionAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            recy1Area.setLayoutManager(linearLayoutManager);
            recy1Area.setAdapter(regionAdper);
        }

        @Override
        public void onFail(String mes) {
            Log.i(TAG, "onFail: "+mes);
        }
    }

    private class AreaCall implements IBackCall<CinemaByRegion> {
        @Override
        public void onSuccess(CinemaByRegion homeShow) {
            List<CinemaByRegionResult> result = homeShow.getResult();
            AreaAdper areaadper = new AreaAdper();
            areaadper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            recy2Area.setLayoutManager(linearLayoutManager);
            recy2Area.setAdapter(areaadper);

        }

        @Override
        public void onFail(String mes) {

        }
    }
}
