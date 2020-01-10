package com.bw.movie.fragment.date;


import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.HotItemAdper;
import com.bw.movie.adper.schedule.ScheduleAdper;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.findHotMovieList.HomeShow;
import com.bw.movie.bean.findHotMovieList.MovieResult;
import com.bw.movie.prentent.HomeHotPrentent;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DateScheduleFragment extends BaseFragment {
    private static final String TAG = "DateScheduleFragment";
    @BindView(R.id.xv_schedule)
    RecyclerView xvSchedule;
    private String s;
    private Unbinder bind;

    public DateScheduleFragment(String s) {
        this.s = s;
    }

    @Override
    protected int onLayout() {
        return R.layout.fragment_dateschedule;
    }

    @Override
    protected void onView(View view) {
        bind = ButterKnife.bind(this, view);
    }

    @Override
    protected void onData() {
        HomeHotPrentent homePrentent = new HomeHotPrentent(new HomehotCall());
        homePrentent.getData(1, 5);
    }

/*    @Subscribe(sticky = true)
    public void EventShow(EventId eventId) {
        int movieId = eventId.getMovieId();
Seat selection
    }*/

    //正在热映
    private class HomehotCall implements IBackCall<HomeShow<List<MovieResult>>> {

        @Override
        public void onSuccess(HomeShow<List<MovieResult>> homeShow) {
            List<MovieResult> result = homeShow.getResult();
            ScheduleAdper scheduleAdper = new ScheduleAdper();
            scheduleAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            xvSchedule.setLayoutManager(linearLayoutManager);
            xvSchedule.setAdapter(scheduleAdper);
        }

        @Override
        public void onFail(String mes) {
            Log.e(TAG, "onFail: "+mes );
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();

    }
}
