package com.bawei.movie.fragment.movefragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bawei.movie.R;
import com.bawei.movie.adper.move.MoveComingAdper;
import com.bawei.movie.base.BaseFragment;
import com.bawei.movie.base.IBackCall;
import com.bawei.movie.bean.findComingSoonMovieList.ComingSoonShow;
import com.bawei.movie.bean.findComingSoonMovieList.FindComingSoonMovieList;
import com.bawei.movie.prentent.HomeComingSoonPrantent;

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


    private class ComingCall implements IBackCall<ComingSoonShow> {
        @Override
        public void onSuccess(ComingSoonShow homeShow) {
            List<FindComingSoonMovieList> result = homeShow.getResult();
            MoveComingAdper moveComingAdper = new MoveComingAdper();
            moveComingAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rvComingSoon.setLayoutManager(linearLayoutManager);
            rvComingSoon.setAdapter(moveComingAdper);

        }

        @Override
        public void onFail(String mes) {

        }
    }
}
