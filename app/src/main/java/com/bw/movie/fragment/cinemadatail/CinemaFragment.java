package com.bw.movie.fragment.cinemadatail;


import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bw.movie.R;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.cinemadetails.CinemaDetails;
import com.bw.movie.bean.eventbean.CinemaIdEven;
import com.bw.movie.bean.findHotMovieList.HomeShow;
import com.bw.movie.prentent.cinemadetails.CinemaDetailPreantent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CinemaFragment extends BaseFragment {

    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_vehicleRoute)
    TextView tvVehicleRoute;
    private Unbinder bind;

    @Override
    protected int onLayout() {
        return R.layout.fragment_cinema;
    }

    @Override
    protected void onView(View view) {
        bind = ButterKnife.bind(this,view);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onData() {

    }

    @Subscribe(sticky = true)
    public void Cinema(CinemaIdEven cinemaIdEven) {
        int cinemaId = cinemaIdEven.getCinemaId();
        CinemaDetailPreantent cinemaDetailPreantent = new CinemaDetailPreantent(new Cinema());
        cinemaDetailPreantent.getData(cinemaId);
    }


    private class Cinema implements IBackCall<HomeShow<CinemaDetails>> {
        @Override
        public void onSuccess(HomeShow<CinemaDetails> homeShow) {
            String address = homeShow.getResult().getAddress();
            tvAddress.setText(address);
            String phone = homeShow.getResult().getPhone();
            tvPhone.setText(phone);
            String vehicleRoute = homeShow.getResult().getVehicleRoute();
            tvVehicleRoute.setText(vehicleRoute);
        }

        @Override
        public void onFail(String mes) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        bind.unbind();
    }
}
