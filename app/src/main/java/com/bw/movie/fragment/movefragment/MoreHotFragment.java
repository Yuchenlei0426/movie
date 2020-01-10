package com.bw.movie.fragment.movefragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.move.MoveHotAdper;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.findHotMovieList.HomeShow;
import com.bw.movie.bean.findHotMovieList.MovieResult;
import com.bw.movie.prentent.HomeHotPrentent;

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


    private class MoveHotCall implements IBackCall<HomeShow<List<MovieResult>>> {


        @Override
        public void onSuccess(HomeShow<List<MovieResult>> homeShow) {
            List<MovieResult> result = homeShow.getResult();
            MoveHotAdper moveHotAdper = new MoveHotAdper();
            moveHotAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            moreRvHot.setLayoutManager(linearLayoutManager);
            moreRvHot.setAdapter(moveHotAdper);

        }

        @Override
        public void onFail(String mes) {

        }
    }
}
