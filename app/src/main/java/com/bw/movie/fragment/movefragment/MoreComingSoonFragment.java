package com.bw.movie.fragment.movefragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.move.MoveComingAdper;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBackCall;

import com.bw.movie.bean.findComingSoonMovieList.FindComingSoonMovieList;
import com.bw.movie.bean.findHotMovieList.HomeShow;
import com.bw.movie.prentent.HomeComingSoonPrantent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MoreComingSoonFragment extends BaseFragment {


    @BindView(R.id.rv_coming_soon)
    RecyclerView rvComingSoon;
    Unbinder unbinder;

    @Override
    protected int onLayout() {
        return R.layout.fragment_coming_soon;
    }

    @Override
    protected void onView(View view) {
        unbinder = ButterKnife.bind(this, view);

    }

    @Override
    protected void onData() {
        HomeComingSoonPrantent homeComingSoonPrantent = new HomeComingSoonPrantent(new ComingCall());
        homeComingSoonPrantent.getData(1, 10);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private class ComingCall implements IBackCall<HomeShow<List<FindComingSoonMovieList>>> {


        @Override
        public void onSuccess(HomeShow<List<FindComingSoonMovieList>> homeShow) {
            List<FindComingSoonMovieList> result = homeShow.getResult();
            MoveComingAdper moveComingAdper = new MoveComingAdper();
            moveComingAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL, false);
            rvComingSoon.setLayoutManager(linearLayoutManager);
            rvComingSoon.setAdapter(moveComingAdper);

        }

        @Override
        public void onFail(String mes) {

        }
    }
}
