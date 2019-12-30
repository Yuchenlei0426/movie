package com.bw.movie.fragment.cinema;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.nearbycinemas.NearbyCinemasAdper;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.eventbean.NearbyCinemasBean;
import com.bw.movie.bean.nearbycinemas.NearbyCinemas;
import com.bw.movie.bean.nearbycinemas.NearbyCinemasResult;
import com.bw.movie.prentent.NearbyCinemasPreantent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class NearbyFragment extends BaseFragment {

    Unbinder unbinder;

    @BindView(R.id.re_nearby)
    RecyclerView reNearby;

    @Override
    protected int onLayout() {
        return R.layout.fragment_nearby;
    }

    @Override
    protected void onView(View view) {
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this, view);

    }

    @Override
    protected void onData() {

    }

    @Subscribe(sticky = true)
    public void show(NearbyCinemasBean nearbyCinemasBean) {
        double latitude = nearbyCinemasBean.getLatitude();
        double longitude = nearbyCinemasBean.getLongitude();
        NearbyCinemasPreantent nearbyCinemasPreantent = new NearbyCinemasPreantent(new NearbyCinemasCall());
        nearbyCinemasPreantent.getData(longitude, latitude, 1, 5);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    private class NearbyCinemasCall implements IBackCall<NearbyCinemas> {
        @Override
        public void onSuccess(NearbyCinemas homeShow) {
            List<NearbyCinemasResult> result = homeShow.getResult();
            NearbyCinemasAdper nearbyCinemasAdper = new NearbyCinemasAdper();
            nearbyCinemasAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            reNearby.setLayoutManager(linearLayoutManager);
            reNearby.setAdapter(nearbyCinemasAdper);

        }

        @Override
        public void onFail(String mes) {

        }
    }


}
