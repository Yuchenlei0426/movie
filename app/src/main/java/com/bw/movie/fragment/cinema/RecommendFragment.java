package com.bw.movie.fragment.cinema;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.cinema.RecommendAdper;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.IBackCall;
import com.bw.movie.bean.recommendcinemas.RecommendCinemasShow;
import com.bw.movie.bean.recommendcinemas.RecommendResult;
import com.bw.movie.prentent.RecommendCinemasPreantent;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class RecommendFragment extends BaseFragment {


    @BindView(R.id.recommend)
    XRecyclerView recommend;
    private Unbinder bind;
    int page=1;
    private ArrayList<RecommendResult> results =new ArrayList<>();


    @Override
    protected int onLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void onView(View view) {
        bind = ButterKnife.bind(this, view);

    }

    @Override
    protected void onData() {
        RecommendCinemasPreantent  recommendCinemasPreantent = new RecommendCinemasPreantent(new RecommendCall());
        recommendCinemasPreantent.getData(page,10);
        recommend.setLoadingMoreEnabled(true);
        recommend.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page++;
                recommend.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page=1;
                recommend.refreshComplete();

            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    private class RecommendCall implements IBackCall<RecommendCinemasShow> {
        @Override
        public void onSuccess(RecommendCinemasShow homeShow) {
            List<RecommendResult> result = homeShow.getResult();
            RecommendAdper recommendAdper = new RecommendAdper();
            recommendAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            recommend.setLayoutManager(linearLayoutManager);
            recommend.setAdapter(recommendAdper);

        }

        @Override
        public void onFail(String mes) {
            Toast.makeText(getActivity(), mes, Toast.LENGTH_SHORT).show();
        }
    }
}
