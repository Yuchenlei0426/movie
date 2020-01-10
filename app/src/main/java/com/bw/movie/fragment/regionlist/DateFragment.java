package com.bw.movie.fragment.regionlist;


import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.InfoByDateAdper;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.cinemas.CinemasInfoByDate;
import com.bw.movie.bean.eventbean.BusEvent;
import com.bw.movie.bean.eventbean.DateEvent;
import com.bw.movie.bean.eventbean.EventId;
import com.bw.movie.bean.findHotMovieList.HomeShow;
import com.bw.movie.prentent.InfoByDatePreantent;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DateFragment extends BaseFragment {

    private static final String TAG = "DateScheduleFragment";
    @BindView(R.id.re_movie)
    XRecyclerView reMovie;
    private int movieId;
    private String date;
    private Unbinder bind;
    int page = 1;
    private InfoByDatePreantent infoByDatePreantent;

    @Override
    protected int onLayout() {
        return R.layout.fragment_date;
    }

    @Override
    protected void onView(View view) {

        bind = ButterKnife.bind(this, view);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onData() {
        infoByDatePreantent = new InfoByDatePreantent(new InfoDate());
        infoByDatePreantent.getData(movieId, date, page, 10);
    }

    @Subscribe(sticky = true)
    public void show(DateEvent dateEvent) {
        date = dateEvent.getDate();
    }

    @Subscribe(sticky = true)
    public void EventShow(EventId eventId) {
        movieId = eventId.getMovieId();

    }


    private class InfoDate implements IBackCall<HomeShow<List<CinemasInfoByDate>>> {
        @Override
        public void onSuccess(HomeShow<List<CinemasInfoByDate>> homeShow) {
            List<CinemasInfoByDate> result = homeShow.getResult();
            if (result != null) {
                InfoByDateAdper infoByDateAdper = new InfoByDateAdper();
                infoByDateAdper.addAll(result);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
                reMovie.setLayoutManager(linearLayoutManager);
                reMovie.setAdapter(infoByDateAdper);
                reMovie.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        //清除集合
                        result.clear();
                        infoByDatePreantent.getData(movieId, date, page, 10);//初始化数据
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                infoByDateAdper.notifyDataSetChanged();//刷新界面
                                reMovie.refreshComplete();     //刷新数据完成（取消刷新动画）
                            }
                        }, 2000);
                    }

                    @Override
                    public void onLoadMore() {
                        page++;//上拉加载添加数据
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                infoByDatePreantent.getData(movieId, date, page, 10);// okhttp解析数据
                                reMovie.loadMoreComplete();    //加载数据完成（取消加载动画）
                            }
                        }, 2000);
                    }
                });

            } else {
                Toast.makeText(getActivity(), "无数据", Toast.LENGTH_SHORT).show();
            }

//            reMovie.setLoadingMoreEnabled(true);
        }

        @Override
        public void onFail(String mes) {
            Log.e(TAG, "onFail: " + mes);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
        EventBus.getDefault().unregister(this);

    }
}
