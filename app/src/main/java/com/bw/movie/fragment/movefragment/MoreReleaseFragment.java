package com.bw.movie.fragment.movefragment;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.move.MoveReleseAdper;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.findReleaseMovieList.FindReleaseMovieList;
import com.bw.movie.bean.findReleaseMovieList.ReleaseShow;
import com.bw.movie.prentent.HomeReleasePrantent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MoreReleaseFragment extends BaseFragment {
    @BindView(R.id.rv_hot_move)
    RecyclerView rvHotMove;
    Unbinder unbinder;

    private static final String TAG = "MoreReleaseFragment";
    @Override
    protected int onLayout() {
        return R.layout.fragment_release;
    }

    @Override
    protected void onView(View view) {
        unbinder = ButterKnife.bind(this, view);

    }

    @Override
    protected void onData() {
        HomeReleasePrantent homeReleasePrantent = new HomeReleasePrantent(new HotCall());
        homeReleasePrantent.getData(1, 10);
    }
    private class HotCall implements IBackCall<ReleaseShow> {
        @Override
        public void onSuccess(ReleaseShow homeShow) {
            List<FindReleaseMovieList> result = homeShow.getResult();
            MoveReleseAdper moveReleseAdper = new MoveReleseAdper();
            moveReleseAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rvHotMove.setLayoutManager(linearLayoutManager);
            rvHotMove.setAdapter(moveReleseAdper);
        }

        @Override
        public void onFail(String mes) {
            Log.i(TAG, "onFail: "+mes);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
