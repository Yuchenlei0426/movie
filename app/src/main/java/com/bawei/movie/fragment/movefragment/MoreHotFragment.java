package com.bawei.movie.fragment.movefragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bawei.movie.R;
import com.bawei.movie.adper.move.MoveHotAdper;
import com.bawei.movie.base.BaseFragment;
import com.bawei.movie.base.IBackCall;
import com.bawei.movie.bean.findHotMovieList.HomeShow;
import com.bawei.movie.bean.findHotMovieList.MovieResult;
import com.bawei.movie.prentent.HomeHotPrentent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MoreHotFragment extends BaseFragment {
    private static final String TAG = "MoreHotFragment";
    @BindView(R.id.more_rv_hot)
    RecyclerView moreRvHot;
    Unbinder unbinder;


    @Override
    protected int onLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void onView(View view) {
        unbinder = ButterKnife.bind(this, view);

    }

    @Override
    protected void onData() {
        HomeHotPrentent homeHotPrentent = new HomeHotPrentent(new MoveHotCall());
        homeHotPrentent.getData(1, 10);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private class MoveHotCall implements IBackCall<HomeShow> {

        @Override
        public void onSuccess(HomeShow homeShow) {
            List<MovieResult> result = homeShow.getResult();
            MoveHotAdper moveHotAdper = new MoveHotAdper();
            moveHotAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            moreRvHot.setLayoutManager(linearLayoutManager);
            moreRvHot.setAdapter(moveHotAdper);

        }

        @Override
        public void onFail(String mes) {

        }
    }
}
