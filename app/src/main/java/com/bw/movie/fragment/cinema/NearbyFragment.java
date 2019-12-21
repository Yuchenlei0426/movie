package com.bw.movie.fragment.cinema;

import android.view.View;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.eventbean.NearbyCinemasBean;
import com.bw.movie.bean.nearbycinemas.NearbyCinemas;
import com.bw.movie.prentent.NearbyCinemasPreantent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class NearbyFragment extends BaseFragment {


    @Override
    protected int onLayout() {
        return R.layout.fragment_nearby;
    }

    @Override
    protected void onView(View view) {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onData() {

    }

    @Subscribe(sticky = true)
    public void show(NearbyCinemasBean nearbyCinemasBean){
        double latitude = nearbyCinemasBean.getLatitude();
        double longitude = nearbyCinemasBean.getLongitude();
        NearbyCinemasPreantent nearbyCinemasPreantent = new NearbyCinemasPreantent(new NearbyCinemasCall());
        nearbyCinemasPreantent.getData(longitude,latitude,1,5);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private class NearbyCinemasCall implements IBackCall<NearbyCinemas> {
        @Override
        public void onSuccess(NearbyCinemas homeShow) {

        }

        @Override
        public void onFail(String mes) {

        }
    }
}
